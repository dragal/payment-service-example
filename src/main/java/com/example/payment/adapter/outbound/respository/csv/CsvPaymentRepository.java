package com.example.payment.adapter.outbound.respository.csv;

import com.example.payment.domain.PaymentRepository;
import com.example.payment.domain.model.Payment;
import com.example.payment.domain.model.PaymentId;
import com.opencsv.CSVWriter;
import com.opencsv.bean.*;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@AllArgsConstructor
@Slf4j
class CsvPaymentRepository implements PaymentRepository {
    private final String filePath;

    private MappingStrategy<CsvPaymentLine> mappingStrategy() {
        ColumnPositionMappingStrategy columnPositionMappingStrategy = new ColumnPositionMappingStrategy();
        columnPositionMappingStrategy.setType(CsvPaymentLine.class);
        return columnPositionMappingStrategy;
    }

    @SneakyThrows
    private List<CsvPaymentLine> readLines() {
        try (FileReader fileReader = new FileReader(filePath)) {
            List<CsvPaymentLine> lines = new CsvToBeanBuilder<CsvPaymentLine>(fileReader)
                    .withMappingStrategy(mappingStrategy())
                    .build()
                    .parse();
            return lines;
        }catch (FileNotFoundException e){
            log.warn("File not found");
            return new ArrayList<>();
        }
    }

    @SneakyThrows
    private void writeLines(List<CsvPaymentLine> lines) {
        Writer writer = new FileWriter(filePath);
        StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer)
                .withMappingStrategy(mappingStrategy())
                .build();
        beanToCsv.write(lines);
        writer.close();
    }

    @Override
    public Optional<Payment> findById(PaymentId id) {
        return readLines().stream()
                .map(line -> line.toPayment())
                .filter(payment -> payment.getId().equals(id))
                .findAny();
    }

    @Override
    public Payment save(Payment payment) {
        List<CsvPaymentLine> lines = readLines();
        if (isNew(payment)) {
            payment = Payment.builder()
                    .id(PaymentId.of(UUID.randomUUID().toString()))
                    .accountNumber(payment.getAccountNumber())
                    .amount(payment.getAmount())
                    .userId(payment.getUserId())
                    .amount(payment.getAmount())
                    .build();
        } else {
            lines.remove(payment);
        }

        CsvPaymentLine csvPaymentLine = CsvPaymentLine.of(payment);
        lines.add(csvPaymentLine);
        writeLines(lines);
        return payment;
    }

    private boolean isNew(Payment payment) {
        return payment.getId() == null;
    }

    @Override
    public List<Payment> findAll() {
        return readLines().stream().map(line -> line.toPayment()).collect(Collectors.toList());
    }

    @Override
    public void deleteById(PaymentId id) {
        List<CsvPaymentLine> lines = readLines();
        lines.removeIf(line -> line.getId().equals(id.value()));
        writeLines(lines);
    }
}

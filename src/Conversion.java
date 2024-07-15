import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Conversion {
    private String fromCurrency;
    private String toCurrency;
    private double amount;
    private double rate;
    private LocalDateTime dateTime;

    public Conversion(String fromCurrency, String toCurrency, double amount, double rate, LocalDateTime dateTime) {
        this.fromCurrency = fromCurrency;
        this.toCurrency = toCurrency;
        this.amount = amount;
        this.rate = rate;
        this.dateTime = dateTime;
    }

    public String getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String formattedDateTime = dateTime.format(formatter);
        return "Data e hora: " + formattedDateTime + "\nValor " + amount + " " + fromCurrency +
                " corresponde ao valor final de =>> " + (amount * rate) + " " + toCurrency;
    }
}

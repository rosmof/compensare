package ro.rosmof.model;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;


/**
 * This solves the putting it all into one piece at page 118
 */
public class TraderTests {

    private static List<Trader> traderList;
    private static List<Transaction> transactionList;


    static {
        traderList = new ArrayList<>();
        traderList.add(new Trader("Ghiran", "Bucuresti"));
        traderList.add(new Trader("Sorin", "Brasov"));
        traderList.add(new Trader("Ion", "Brasov"));
        traderList.add(new Trader("Razvan", "Londra"));

        transactionList = new ArrayList<>();
        transactionList.add(new Transaction(traderList.get(0), 2011, 134));
        transactionList.add(new Transaction(traderList.get(0), 2012, 342));
        transactionList.add(new Transaction(traderList.get(1), 2012, 155));
        transactionList.add(new Transaction(traderList.get(1), 2013, 398));
        transactionList.add(new Transaction(traderList.get(1), 2011, 970));
        transactionList.add(new Transaction(traderList.get(2), 2014, 560));
        transactionList.add(new Transaction(traderList.get(2), 2012, 433));
        transactionList.add(new Transaction(traderList.get(2), 2013, 989));
        transactionList.add(new Transaction(traderList.get(3), 2011, 423));
        transactionList.add(new Transaction(traderList.get(3), 2014, 987));
    }

    @Test
    public void allTransactions() {

        //first question
        List<Transaction> filteredByYear = transactionList
                .stream()
                .filter(t -> t.getYear() > 2011)
                .sorted(Comparator.comparingInt(Transaction::getValue))
                .collect(Collectors.toList());

        //second question
        List<String> uniqueCities = transactionList.stream().map(t -> t.getTrader().getCity()).distinct().collect(Collectors.toList());

        //third question
        List<Trader> traders = transactionList
                .stream()
                .map(Transaction::getTrader)
                .filter(t -> t.getCity().equals("Brasov"))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());

        //forth question
        String result = transactionList
                .stream()
                .map(t -> t.getTrader().getName())
                .distinct()
                .sorted(Comparator.comparing(String::toLowerCase))
                .reduce("", (a, b) -> a + " " + b);

        //fifth question
        Optional<Trader> cityTraders = transactionList
                .stream()
                .map(Transaction::getTrader)
                .filter(a -> a.getCity().equals("Milan"))
                .findAny();

        //alternative solution
        boolean isMilan = transactionList
                .stream()
                .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));

        //sixth question
        Integer max = transactionList
                .stream()
                .map(Transaction::getValue)
                .reduce(Integer::max).orElse(0);

        Integer min = transactionList
                .stream()
                .map(Transaction::getValue)
                .reduce(Integer::min)
                .orElse(0);

        System.out.println("stop");

        Stream<Integer> sit = Stream.iterate(1, bit -> bit * 2).limit(17);
        sit.forEach(System.out::println);

        List<String> fr = Arrays.asList("ghiran", "alexandru");
        Map<Trader, List<Transaction>> mp = transactionList.stream().collect(groupingBy(Transaction::getTrader));
        System.out.println("dadasd");
    }

}

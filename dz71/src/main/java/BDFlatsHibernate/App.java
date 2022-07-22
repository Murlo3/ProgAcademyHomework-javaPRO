package BDFlatsHibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class App {
    static EntityManagerFactory emf;
    static EntityManager em;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            // create connection
            emf = Persistence.createEntityManagerFactory("JPATest");
            em = emf.createEntityManager();
            try {
                while (true) {
                    System.out.println("1: add Flat");
                    System.out.println("2: add random Flat");
                    System.out.println("3: delete Flat");
                    System.out.println("4: change Flat");
                    System.out.println("5: view Flats");
                    System.out.println("6: view Flat for your param");
                    System.out.print("-> ");

                    String s = sc.nextLine();
                    switch (s) {
                        case "1":
                            addFlat(sc);
                            break;
                        case "2":
                            insertRandomFlat(sc);
                            break;
                        case "3":
                            deleteFlat(sc);
                            break;
                        case "4":
                            changeFlat(sc);
                            break;
                        case "5":
                            viewFlats();
                            break;
                        case "6":
                            viewFlatsByParam(sc);
                            break;
                        default:
                            return;
                    }
                }
            } finally {
                sc.close();
                em.close();
                emf.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        }
    }

    private static void addFlat(Scanner sc) {
        System.out.print("Enter Flat region: ");
        String region = sc.nextLine();
        System.out.print("Enter Flat address: ");
        String address = sc.nextLine();
        System.out.print("Enter Flat area: ");
        String sFlatArea = sc.nextLine();
        double flatArea = Double.parseDouble(sFlatArea);
        System.out.print("Enter number of rooms: ");
        String sNumberOfRooms = sc.nextLine();
        int numberOfRooms = Integer.parseInt(sNumberOfRooms);
        System.out.print("Enter Flat price: ");
        String sPrice = sc.nextLine();
        double price = Double.parseDouble(sPrice);

        em.getTransaction().begin();
        try {
            Flat one = new Flat(region, address, flatArea, numberOfRooms, price);
            em.persist(one);
            em.getTransaction().commit();

            System.out.println(one.getId());
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }
    private static void insertRandomFlat(Scanner sc) {
        System.out.print("Enter Flats count: ");
        String sCount = sc.nextLine();
        int count = Integer.parseInt(sCount);

        em.getTransaction().begin();
        try {
            for (int i = 0; i < count; i++) {
                Flat c = new Flat(randomRegion(),randomAddress(),RND.nextDouble(150), RND.nextInt(4),RND.nextDouble(200000.0));
                em.persist(c);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }
    private static void deleteFlat(Scanner sc) {
    }
    private static void changeFlat(Scanner sc) {
    }
    private static void viewFlats() {
        Query query = em.createQuery("SELECT x FROM Flat x", Flat.class);
        List<Flat> list = (List<Flat>) query.getResultList();

        for (Flat c : list)
            System.out.println(c);
    }
    private static void viewFlatsByParam(Scanner sc) {
        System.out.println("You need to enter the parameters of the Flat as in the example");
        System.out.println("EXAMPLE: You want a flat with 3 rooms, up to 92 m2, in Soloma area and the price is not higher than 80,000");
        System.out.println("Your request should look like this: 'Soloma,92,3,80000' ");
        System.out.println("Enter your params 'region,flatArea,numberOfRooms,price': ");
        String param = sc.nextLine();     //region,area,rooms,price
        String[] params = param.split(",");
//        for (String str : params) {
//            if(str == null) str = ;
//        }
        String region = params[0];
        double flatArea = Double.parseDouble(params[1]);    //flatArea до введенного значения
        int numberOfRooms = Integer.parseInt(params[2]);
        double price = Double.parseDouble(params[3]);       //price цена до какого-то значения

        Query query = em.createQuery("SELECT x FROM Flat x WHERE x.region IN ('"+region+"') " +
                "AND x.flatArea < "+flatArea+" " +
                "AND x.numberOfRooms = "+numberOfRooms+" " +
                "AND x.price < "+price, Flat.class);
        List<Flat> list = (List<Flat>) query.getResultList();

        for (Flat c : list)
            System.out.println(c);
    }

    static final String[] REGIONS = {"Soloma", "Dnipro", "Borchshaga", "Troya", "VDNG"};
    static final String[] ADDRESSES = {"Metalistiv,22", "Naberezhna,12", "Kiltseva,2", "hzhzhzhz,1432", "Parkova,14"};
    static final Random RND = new Random();
    static String randomRegion() {
        return REGIONS[RND.nextInt(REGIONS.length)];
    }
    static String randomAddress() {
        return ADDRESSES[RND.nextInt(ADDRESSES.length)];
    }
}

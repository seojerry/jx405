package javabasic.videomanager.viewer;


import javabasic.util.ScannerUtil;
import javabasic.videomanager.controller.CustomerController;
import javabasic.videomanager.controller.FilmController;
import javabasic.videomanager.controller.InventoryController;
import javabasic.videomanager.controller.RentalController;
import javabasic.videomanager.model.CustomerDTO;
import javabasic.videomanager.model.InventoryDTO;
import javabasic.videomanager.model.RentalDTO;
import javabasic.videomanager.model.StaffDTO;


import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class RentalViewer {
    private RentalController rentalController;
    private Connection connection;


    private final Scanner SCANNER;
    private final String DATE_FORMAT = "yy/MM/dd HH:mm:ss";
    private StaffDTO logIn;

    public RentalViewer(Scanner scanner, Connection connection,StaffDTO logIn) {
        this.connection = connection;
        rentalController = new RentalController(this.connection);
        this.logIn = logIn;
        SCANNER = scanner;
    }

    public void function() {
        FilmController filmController = new FilmController(connection);
        InventoryController inventoryController = new InventoryController(connection);
        CustomerController customerController = new CustomerController(connection);
        String message = "First Name을 입력해주세요.";
        String first_name = ScannerUtil.nextLine(SCANNER, message);
        message = "Last Name을 입력해주세요.";
        String last_name = ScannerUtil.nextLine(SCANNER, message);
        message = "Email을 입력해주세요.";
        String email = ScannerUtil.nextLine(SCANNER, message);
        while (true) {
            RentalDTO rentalDTO = new RentalDTO();
            message = "비디오 이름을 입력해주세요.";
            String film_name = ScannerUtil.nextLine(SCANNER, message);

            //비디오 이름으로 film_id 추출
            int film_id = filmController.selectOneByTitle(film_name).getFilm_id();

            //film_id로 inventory id 리스트 접근
            ArrayList<InventoryDTO> i_list = inventoryController.selectList(film_id,logIn);
            if (i_list.size() == 0) {
                message ="해당 비디오는 가게에 존재하지않습니다. 다른 비디오를 원하시면 1, 취소하고싶으시면 0을 입력해주세요.";
                int userChoice = ScannerUtil.nextInt(SCANNER, message,0,1);
                if (userChoice == 1) {
                    continue;
                } else if (userChoice == 0) {
                    break;
                }
            }
            //해당 inventory id들로 rental테이블을 돌며 대여가능 여부 확인
            ArrayList<RentalDTO> r_list = rentalController.selectAll();
            int inventory_id = checkInventory(i_list, r_list);
            if(inventory_id == -1){
                message ="비디오 재고가 없습니다. 다른 비디오를 원하시면 1, 취소하고싶으시면 0을 입력해주세요.";
                int userChoice = ScannerUtil.nextInt(SCANNER, message,0,1);
                if (userChoice == 1) {
                    continue;
                } else if (userChoice == 0) {
                    break;
                }
            } else {
                if (customerController.selectOneByEmail(email) == null) {
                    CustomerDTO customerDTO = new CustomerDTO();
                    customerDTO.setFirst_name(first_name);
                    customerDTO.setLast_name(last_name);
                    customerDTO.setStore_id(logIn.getStore_id());
                    customerDTO.setEmail(email);
                    customerController.insert(customerDTO);
                }
                rentalDTO.setStaff_id(logIn.getStaff_id());
                rentalDTO.setCustomer_id(customerController.selectOneByEmail(email).getCustomer_id());
                rentalDTO.setInventory_id(inventory_id);
                rentalController.insert(rentalDTO);
                System.out.println("대여 완료되었습니다.");
                break;
            }
        }

    }

    public int checkInventory(ArrayList<InventoryDTO> inventoryList, ArrayList<RentalDTO> rentalList) {
        for (InventoryDTO i : inventoryList) {
            boolean flag = false;
            boolean flag2 = false;
            for (RentalDTO r : rentalList) {
                if(r.getInventory_id() == i.getInventory_id()){
                    flag = true;
                }
                if (r.getInventory_id() == i.getInventory_id() && r.getReturn_date() == null) {
                    flag2 = true;
                }
            }
            if(flag2==false){
                return i.getInventory_id();
            }
            if (flag == false) {
                return i.getInventory_id();
            }
        }
        return -1;
    }

    public void returnVideo() {
        FilmController filmController = new FilmController(connection);
        InventoryController inventoryController = new InventoryController(connection);
        CustomerController customerController = new CustomerController(connection);
        ArrayList<RentalDTO> r_list = rentalController.selectAll();
        String message = "email을 입력해주세요.";
        String email = ScannerUtil.nextLine(SCANNER, message);
        message = "영화 이름을 입력해주세요.";
        String film_name = ScannerUtil.nextLine(SCANNER, message);
        int film_id = filmController.selectOneByTitle(film_name).getFilm_id();
        ArrayList<InventoryDTO> i_list = inventoryController.selectList(film_id,logIn);
        int customer_id = customerController.selectOneByEmail(email).getCustomer_id();
        for (InventoryDTO i : i_list) {
            for (RentalDTO r : r_list) {
                if (i.getInventory_id() == r.getInventory_id() && r.getCustomer_id() == customer_id && r.getReturn_date()==null) {
                    RentalDTO rentalDTO = new RentalDTO();
                    rentalDTO.setRental_id(r.getRental_id());
                    rentalDTO.setRental_date(r.getRental_date());
                    rentalDTO.setStaff_id(logIn.getStaff_id());
                    rentalDTO.setCustomer_id(customerController.selectOneByEmail(email).getCustomer_id());
                    rentalDTO.setInventory_id(i.getInventory_id());
                    rentalController.update(rentalDTO);
                    System.out.println("반납 되었습니다.");
                    return;
                }
            }
        }
        System.out.println("반납할 정보가 없습니다. 다시 입력해주세요.");
    }
    public void printList() {
        ArrayList<RentalDTO> list = rentalController.selectAll();
        InventoryController inventoryController = new InventoryController(connection);
        FilmController filmController = new FilmController(connection);
        CustomerController customerController = new CustomerController(connection);
        if (list.isEmpty()) {
            System.out.println("아직 등록된 대여정보가 없습니다.");
        } else {
            for (RentalDTO r : list) {
                int film_id = inventoryController.selectOne(r.getInventory_id()).getFilm_id();
                String title = filmController.selectOne(film_id).getTitle();
                String email = customerController.selectOne(r.getCustomer_id()).getEmail();
                DateFormat df = new SimpleDateFormat(DATE_FORMAT);
                String return_date;
                if (r.getReturn_date() != null) {
                    return_date = df.format(r.getRental_date());
                }else{
                    return_date ="미반납";
                }
                String staff = logIn.getFirst_name();

                System.out.printf("%d. 대여날짜:%s 영화:%s 고객:%s 반납일:%s 직원:%s\n", r.getRental_id(), df.format(r.getRental_date()), title, email, return_date, staff);
            }

        }
    }
}

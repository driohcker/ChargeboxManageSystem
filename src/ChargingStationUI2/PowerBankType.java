package ChargingStationUI2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class PowerBankType {
    private String type;
    private double rentalPrice;

    public PowerBankType(String type, double rentalPrice) {
        this.type = type;
        this.rentalPrice = rentalPrice;
    }

    public String getType() {
        return type;
    }

    public double getRentalPrice() {
        return rentalPrice;
    }
}

class PowerBank {
    private String id;
    private PowerBankType type;
    private boolean isRented;

    public PowerBank(String id, PowerBankType type) {
        this.id = id;
        this.type = type;
        this.isRented = false;
    }

    public String getId() {
        return id;
    }

    public PowerBankType getType() {
        return type;
    }

    public boolean isRented() {
        return isRented;
    }

    public void setRented(boolean rented) {
        isRented = rented;
    }
}

class User {
    private String username;
    private String password;
    private List<PowerBank> rentedPowerBanks;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.rentedPowerBanks = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<PowerBank> getRentedPowerBanks() {
        return rentedPowerBanks;
    }
}

class ChargingStationManager {
    private Map<String, PowerBankType> powerBankTypes;
    private Map<String, PowerBank> powerBanks;
    private Map<String, User> users;

    public ChargingStationManager() {
        powerBankTypes = new HashMap<>();
        powerBanks = new HashMap<>();
        users = new HashMap<>();
    }

    // 添加充电宝类型
    public void addPowerBankType(String type, double rentalPrice) {
        powerBankTypes.put(type, new PowerBankType(type, rentalPrice));
    }

    // 添加充电宝
    public void addPowerBank(String id, String type) {
        if (powerBankTypes.containsKey(type)) {
            powerBanks.put(id, new PowerBank(id, powerBankTypes.get(type)));
        }
    }

    // 注册用户
    public void registerUser(String username, String password) {
        users.put(username, new User(username, password));
    }

    // 租用充电宝
    public boolean rentPowerBank(String username, String powerBankId) {
        if (users.containsKey(username) && powerBanks.containsKey(powerBankId) && !powerBanks.get(powerBankId).isRented()) {
            users.get(username).getRentedPowerBanks().add(powerBanks.get(powerBankId));
            powerBanks.get(powerBankId).setRented(true);
            return true;
        }
        return false;
    }

    // 归还充电宝
    public boolean returnPowerBank(String username, String powerBankId) {
        if (users.containsKey(username) && powerBanks.containsKey(powerBankId) && powerBanks.get(powerBankId).isRented()) {
            users.get(username).getRentedPowerBanks().remove(powerBanks.get(powerBankId));
            powerBanks.get(powerBankId).setRented(false);
            return true;
        }
        return false;
    }

    // 计算费用
    public double calculateFee(String username) {
        double totalFee = 0;
        if (users.containsKey(username)) {
            for (PowerBank pb : users.get(username).getRentedPowerBanks()) {
                totalFee += pb.getType().getRentalPrice();
            }
        }
        return totalFee;
    }
}

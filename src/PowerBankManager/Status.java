package PowerBankManager;

/**
 * 此模块实现对充电宝的增删查的操作
 */
public enum Status {
    AVAILABLE("available"),
    RENTAL("rental"),
    MAINTENANCE("maintenance");

    private final String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}

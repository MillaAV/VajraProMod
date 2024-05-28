package millaav.vajrapro.common.Item;

public enum EnumUpgradeType {
    AUTOMELTING("automelting"),
    SILKTOUCH("silktouch"),
    RANGE("range"),
    FORTUNA("fortuna"),
    DEPTH("depths"),
    DAMAGE("damage"),
    HOE("hoe"),
    DAMAGE1("damage1"),
    DAMAGE2("damage2"),
    DEPTH1("depth1"),
    DEPTH2("depth2"),
    FORTUNA1("fortuna1"),
    FORTUNA2("fortuna2");

    public final String key;
    EnumUpgradeType(String key){
        this.key = key;
    }
}

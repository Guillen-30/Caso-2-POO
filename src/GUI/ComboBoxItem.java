package src.GUI;

public class ComboBoxItem<T> {
    private Integer displayId;
    private T associatedObject;

    public ComboBoxItem(Integer displayId, T associatedObject) {
        this.displayId = displayId;
        this.associatedObject = associatedObject;
    }

    public Integer getDisplayId() {
        return displayId;
    }

    public T getAssociatedObject() {
        return associatedObject;
    }

    @Override
    public String toString() {
        return displayId.toString();
    }
}

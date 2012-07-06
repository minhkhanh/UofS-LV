package emenu.client.menu.view;

public interface ICheckable {
    void joinGroup(SingleChoiceGroup group);

    void toggle();

    boolean isChecked();

    void setChecked(boolean value);
}

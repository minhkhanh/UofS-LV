package client.menu.ui.view;

public interface CheckableInterface {
    void joinGroup(SingleChoiceGroup group);

    void toggle();

    boolean isChecked();

    void setChecked(boolean value);
}

package SOLID.interface_seggregation.bad;

public class FileDaoConnection implements DAOInterface{

    @Override
    public void openConnection() {
        // we cant open connection in file system
    }

    @Override
    public void createRecord() {

    }

    @Override
    public void openFile() {

    }

    @Override
    public void deleteRecord() {

    }
}

package SOLID.interface_seggregation.bad;

public class DBDaoConnection implements DAOInterface{
    @Override
    public void openConnection() {

    }

    @Override
    public void createRecord() {

    }

    @Override
    public void openFile() {
        // We are in DB Connection so no need to support open file
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteRecord() {

    }
}

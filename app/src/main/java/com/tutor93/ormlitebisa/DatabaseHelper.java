package com.tutor93.ormlitebisa;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by indra on 11/09/2016.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private Context _context;
    private static final String DATABASE_NAME = "ormliteandroid.db";
    private static final int DATABASE_VERSION = 1;
    private Dao<Employee, String> simpleDao = null;
    private RuntimeExceptionDao<Employee, String> simpleRuntimeDao = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        _context = context;
    }

    public Dao<Employee, String> getDao() throws SQLException {
        if (simpleDao == null) {
            simpleDao = getDao(Employee.class);
        }
        return simpleDao;
    }

    public RuntimeExceptionDao<Employee, String> getSimpleDataDao() {
        if (simpleRuntimeDao == null) {
            simpleRuntimeDao = getRuntimeExceptionDao(Employee.class);
        }
        return simpleRuntimeDao;
    }


    //method for list of emp START
    /*What its mean context ???*/
    public List<Employee> GetData() {
        DatabaseHelper helper = new DatabaseHelper(_context);
        RuntimeExceptionDao<Employee, String> simpleDao = helper
                .getSimpleDataDao();
        List<Employee> list = simpleDao.queryForAll();
        return list;
    }
    /*END*/

    //    Method for insert data
    public int addData(Employee emp) {
        RuntimeExceptionDao<Employee, String> dao = getSimpleDataDao();
        int i = dao.create(emp);
        return i;
    }

    //  method for delete all rows
    public void deleteAll() {
        RuntimeExceptionDao<Employee, String> dao = getSimpleDataDao();
        List<Employee> list = dao.queryForAll();
        dao.delete(list);
    }

    @Override
    public void close() {
        super.close();
        simpleRuntimeDao = null;
    }


    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            Log.i(DatabaseHelper.class.getName(), "onCreate");
            TableUtils.createTable(connectionSource, Employee.class);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "can't create database", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource,
                          int oldVersion, int newVersion) {
            try {
                Log.i(DatabaseHelper.class.getName(), "onUpgrade");
                TableUtils.dropTable(connectionSource, Employee.class, true);
                onCreate(database, connectionSource);
            }catch (SQLException e){
                Log.e(DatabaseHelper.class.getName(), "can't drop database", e);
                throw new RuntimeException(e);
            }
    }
}

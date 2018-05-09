package com.wzrd.m.db;

import android.content.Context;

import com.wzrd.m.been.User;
import com.wzrd.m.db.gen.DaoSession;
import com.wzrd.m.db.gen.UserDao;

import java.util.List;

/**
 * Created by Administrator on 2017/1/17.
 */
public class UserFormManager {
    private UserDao mUserDao;
    private static UserFormManager mUserManager;

    public UserFormManager(Context context) {
        DaoSession daoSession = GreenDaoHelper.getInstance().getDaoSession(context);
        mUserDao = daoSession.getUserDao();
    }

    public static UserFormManager getInstance(Context context) {
        if (mUserManager == null) {
            mUserManager = new UserFormManager(context);
        }
        return mUserManager;
    }

    /**
     * 完成对数据库表的插入操作
     *
     * @param User
     * @return
     */
    public boolean insertUser(User User) {
        boolean flag = false;
        flag = mUserDao.insertOrReplace(User) != -1 ? true : false;
        return flag;
    }

    /**
     * 完成对数据库的多次插入
     * 完成对数据库的多次插入
     *
     * @param Users
     */
    public void insertMultUser(final List<User> Users) {
        mUserDao.insertOrReplaceInTx(Users);
    }

    /**
     * 更新对user某一条记录的修改
     *
     * @param User
     */
    public void updateUser(User User) {
        mUserDao.update(User);
    }


    /**
     * 删除一条数据
     *
     * @param User
     */
    public void deleteUser(User User) {
        mUserDao.delete(User);
    }

    /**
     * 返回多行记录
     *
     * @return
     */
    public List<User> getAllUser() {
        return mUserDao.loadAll();
    }

//    public List<User> getByF_FORM_IDs(ArrayList<String> F_FORM_IDs){
//        QueryBuilder<User> builder = mUserDao.queryBuilder();
//        QueryBuilder<User> Users = builder.where(UserDao.Properties.F_FORM_ID.in(F_FORM_IDs));
//        return Users.list();
//    }



    public void clear(){
        mUserDao.deleteAll();
    }


}

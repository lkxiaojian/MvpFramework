package com.wzrd.m.been;

import android.databinding.BaseObservable;
import android.view.View;
import android.widget.Toast;

import java.util.List;

/**
 * Created by lk on 2017/9/18.
 */

public class Loginbeen extends BaseObservable {


    /**
     * code : 0
     * description : 你接收到了吗？
     * dataobj : [{"projId":"8a8412915aaba020015aabb0c4220183","id":"8a8412915ad24b7b015ad7852a0c11de","loginname":"aqzjj","name":"安全总监甲","password":"23515930","sex":"1","status":"1","type":"1"}]
     */

    private String code;
    private String description;
    private List<DataobjBean> dataobj;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<DataobjBean> getDataobj() {
        return dataobj;
    }

    public void setDataobj(List<DataobjBean> dataobj) {
        this.dataobj = dataobj;
    }

    public static class DataobjBean  extends BaseObservable{
        /**
         * projId : 8a8412915aaba020015aabb0c4220183
         * id : 8a8412915ad24b7b015ad7852a0c11de
         * loginname : aqzjj
         * name : 安全总监甲
         * password : 23515930
         * sex : 1
         * status : 1
         * type : 1
         */

        private String projId;
        private String id;
        private String loginname;
        private String name;
        private String password;
        private String sex;
        private String status;
        private String type;

        public String getProjId() {
            return projId;
        }

        public void setProjId(String projId) {
            this.projId = projId;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLoginname() {
            return loginname;
        }

        public void setLoginname(String loginname) {
            this.loginname = loginname;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
        public void click(View view){
            Toast.makeText(view.getContext(),Loginbeen.DataobjBean.this.getLoginname(),Toast.LENGTH_SHORT).show();
        }
    }

}

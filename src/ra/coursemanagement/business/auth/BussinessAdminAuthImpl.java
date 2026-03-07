package ra.coursemanagement.business.auth;

import org.mindrot.jbcrypt.BCrypt;
import ra.coursemanagement.dao.impl.AdminDAOImpl;
import ra.coursemanagement.dao.itf.IAdminDAO;
import ra.coursemanagement.model.entity.Admin;

public class BussinessAdminAuthImpl implements IAdminAuth {
    private IAdminDAO adminDAO =  new AdminDAOImpl();

    @Override
    public boolean loginAdmin(String username, String password) {
        Admin admin = adminDAO.findAdminByusername(username);
        if(admin == null){
            return false;
        }else {
            return BCrypt.checkpw(password, admin.getPassword());

        }

    }
}


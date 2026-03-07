package ra.coursemanagement.dao.itf;

import ra.coursemanagement.model.entity.Admin;

public interface IAdminDAO {
    Admin findAdminByusername(String username);
}

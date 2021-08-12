package com.chidorirasengan.config.dao;
/**
 * See some comments later...
 * @author crhaisdeonrgian [https://github.com/Crhaisdeonrgian]
 */
import com.chidorirasengan.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class SignupDAOImpl implements SignupDAO {
    @Autowired
    private JdbcTemplate JdbcTemplate;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public void saveUser(UserModel userModel){
        String sql = "insert into users values(?,?,?)";
        String sql2 = "insert into authorities values(?,?)";
        JdbcTemplate.update(sql, userModel.getUsername(), passwordEncoder.encode(userModel.getPassword()), true);
        JdbcTemplate.update(sql2, userModel.getUsername(),"USER");
    }
}

package com.violox.tentag.entity;

import com.violox.tentag.domain.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.sql.DataSource;

@ApplicationScoped
public class GroupAttributeFilter implements AttributeFilter<Group, String> {

    public static String ROLE_NAME = "u.role_name";

    @Resource(name = "jdbc/TentagDatabaseResource")
    @ApplicationScoped
    private DataSource ds;

    @Override
    public ArrayList<Group> getByAttribute(String column, String value) {
        ArrayList<Group> ret = new ArrayList<>();
        String sql = String.format("select "
                + "  g.group_id "
                + ", g.group_name "
                + ", g.role_name "
                + "from "
                + "tentag.user u "
                + "join tentag.user_group ug on u.user_id = ug.user_id "
                + "join tentag.`group` g on ug.group_id = g.group_id "
                + "where " + column + " = '%s'; ", value);
        try (Connection conn = ds.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Group item = new Group();
                item.setId(rs.getInt("group_id"));
                item.setName(rs.getString("group_name"));
                item.setRole(rs.getString("role_name"));
                ret.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GroupRelation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ret;

    }

}

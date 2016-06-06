/*
 * Copyright 2016 mopote.com
 *
 * The Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package org.bigmouth.fuhsi.servicelogic.plugin.demo.pair.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.bigmouth.fuhsi.servicelogic.plugin.demo.pair.Pair;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;


public class DbPairDao implements PairDao {

    private final JdbcTemplate jdbcTemplate;
    
    public DbPairDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Pair queryByKey(String key) {
        final String sql = "SELECT key, value, des, client_use FROM tbl_fm_base_conf_pair WHERE deleted = 0 AND key=?";
        final Object[] args = new Object[] {key};
        return jdbcTemplate.query(sql, args, new ResultSetExtractor<Pair>() {

            @Override
            public Pair extractData(ResultSet rs) throws SQLException, DataAccessException {
                Pair p = new Pair();
                p.setId(rs.getLong("id"));
                p.setKey(rs.getString("key"));
                p.setValue(rs.getString("value"));
                p.setDesc(rs.getString("des"));
                return p;
            }
        });
    }

    @Override
    public List<Pair> queryAll() {
        final String sql = "SELECT id, key, value, des, client_use FROM tbl_fm_base_conf_pair WHERE deleted = 0 ORDER BY create_time DESC";
        return jdbcTemplate.query(sql, new RowMapper<Pair>() {

            @Override
            public Pair mapRow(ResultSet rs, int rowNum) throws SQLException {
                Pair p = new Pair();
                p.setId(rs.getLong("id"));
                p.setKey(rs.getString("key"));
                p.setValue(rs.getString("value"));
                p.setDesc(rs.getString("des"));
                return p;
            }
        });
    }
}

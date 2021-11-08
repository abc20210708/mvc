package com.spring.mvc;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DbConnectTest {

    //DB 접속 정보 설정
    private String id = "hr";
    private String pw = "hr";
    private String url = "jdbc:oracle:thin:@localhost:1521:xe";

    @Test
    void connectTest() {
        try {
            //1. 드라이버 커넥터 동적 로딩
            Class.forName("oracle.jdbc.driver.OracleDriver");

            //2. 연결 정보 생성
            Connection conn
                    = DriverManager.getConnection(url, id, pw);
            System.out.println("DB 연결 성공! = " + conn);

            //3. SQL 실행하기
            String sql = "SELECT first_name, salary FROM employees";
            //SQL 실행 객체
            PreparedStatement pstmt
                    = conn.prepareStatement(sql);

            ///표
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String name = rs.getString("first_name");
                int salary = rs.getInt("salary");
                System.out.println("name = " + name +", salary = " + salary);
            }

        } catch (Exception e) {
            System.out.println("DB 연결 실패!!");
            e.printStackTrace();
        }
    }

}//end class

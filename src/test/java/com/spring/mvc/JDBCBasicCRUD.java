package com.spring.mvc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class JDBCBasicCRUD {

    //DB 접속 정보 설정
    private String id = "spring4";
    private String pw = "1234";
    private String url = "jdbc:oracle:thin:@localhost:1521:xe";
    private String driver = "oracle.jdbc.driver.OracleDriver";

    @Test
    @DisplayName("product 데이터를 추가해야 한다.")
    void insertTest() {

        try {
            //1. 드라이버 로딩
            Class.forName(driver);
            //2. 연결 정보 객체 생성
            Connection conn
                    = DriverManager.getConnection(url, id, pw);
            //3. SQL 실행 객체 생성
            String sql = "INSERT INTO product VALUES (seq_product.nextval, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            //4. ?값 채우기
            pstmt.setString(1, "공기청정기");
            pstmt.setInt(2, 670000);

            //5. SQL 실행 명령
            //a : INSERT, UPDATE, DELETE  - executeUpdate()
            //b : SELECT                   - executeQuery()
            int resultNum = pstmt.executeUpdate(); //성공한 쿼리의 수 리턴
            if (resultNum == 1) {
                System.out.println("입력 성공!");
            }
            Assertions.assertTrue(resultNum == 1);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }//

    @Test
    @DisplayName("product 데이터를 수정해야 한다.")
    void updateTest() {

        try (Connection conn
                     = DriverManager.getConnection(url, id, pw)) {
            //1. 드라이버 로딩
            Class.forName(driver);
            //2. 연결 정보 객체 생성 - try() ->

            //3. SQL 실행 객체 생성
            String sql = "UPDATE product SET product_name=?, product_price=? WHERE product_id=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            //4. ?값 채우기
            pstmt.setString(1, "텔레비전_fix");
            pstmt.setInt(2, 2000000);
            pstmt.setInt(3, 2);

            //5. SQL 실행 명령
            //a : INSERT, UPDATE, DELETE  - executeUpdate()
            //b : SELECT                   - executeQuery()
            int resultNum = pstmt.executeUpdate(); //성공한 쿼리의 수 리턴
            if (resultNum == 1) {
                System.out.println("수정 성공!");
            }
            Assertions.assertTrue(resultNum == 1);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }//

    @Test
    @DisplayName("product 데이터를 삭제해야 한다.")
    void deleteTest() {

        try (Connection conn
                     = DriverManager.getConnection(url, id, pw)) {
            //1. 드라이버 로딩
            Class.forName(driver);
            //2. 연결 정보 객체 생성 - try() ->

            //3. SQL 실행 객체 생성
            String sql = "DELETE FROM product WHERE product_id=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            //4. ?값 채우기
            pstmt.setInt(1,1);

            //5. SQL 실행 명령
            //a : INSERT, UPDATE, DELETE  - executeUpdate()
            //b : SELECT                   - executeQuery()
            int resultNum = pstmt.executeUpdate(); //성공한 쿼리의 수 리턴
            if (resultNum == 1) {
                System.out.println("삭제 성공!");
            }
            Assertions.assertTrue(resultNum == 1);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }//


}//end class

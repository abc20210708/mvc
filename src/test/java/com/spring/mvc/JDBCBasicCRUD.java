package com.spring.mvc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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


    @Test
    @DisplayName("product 테이블 전체 조회해야 한다.")
    void findAllTest() {

        ///자원 반납 자동 try()
        try (Connection conn
                     = DriverManager.getConnection(url, id, pw)) {
            //1. 드라이버 로딩
            Class.forName(driver);
            //2. 연결 정보 객체 생성 - try() ->

            //3. SQL 실행 객체 생성
            String sql = "SELECT * FROM product";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            //4. ?값 채우기


            //5. SQL 실행 명령
            //a : INSERT, UPDATE, DELETE  - executeUpdate()
            //b : SELECT                   - executeQuery()
            ResultSet rs = pstmt.executeQuery();
            ///ResultSet 표
/*
            boolean b1 = rs.next();
            boolean b2 = rs.next();

            System.out.println("b1 = " + b1);
            System.out.println("b2 = " + b2);*/

            while (rs.next()) {
                String name = rs.getString("product_name");
                int price = rs.getInt("product_price");
                System.out.printf("제품명 : %s, 가격: %d원\n," ,name , price );
            }


         /*   System.out.println("name = " + name);
            System.out.println("price = " + price);

            boolean b3 = rs.next();
            System.out.println("b3 = " + b3);*/

        } catch (Exception e) {
            e.printStackTrace();
        }

    }//


}//end class

package com.bishe.teamproject.jwt;

import com.bishe.teamproject.model.UserInfo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.StringUtils;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
public class JwtUtil {

    //过期时间设置
    public static final long EXPIRE = 1000 * 60 * 30 ;//30分钟
    //生成密码
    public static final String APP_SECRET = "kappy";

    /**
     * 生成token的方法
     * @param user
     * @return
     */

    public static String getJwtToken(UserInfo user){
        String JwtToken = Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .claim("username", user.getUsername())
                .claim("type", user.getType())
                .claim("id", user.getId())
                .signWith(SignatureAlgorithm.HS256, APP_SECRET)
                .compact();
        return JwtToken;
    }
    /**
     * 判断token是否存在与有效
     * @param jwtToken
     * @return
     */
    public static boolean checkToken(String jwtToken) {
        if(StringUtils.isEmpty(jwtToken)) return false;
        try {
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    /**
     * 判断token是否存在与有效
     * @param request
     * @return
     */
    public static boolean checkToken(HttpServletRequest request) {
        try {
            String jwtToken = request.getHeader("token");
            if(StringUtils.isEmpty(jwtToken)) return false;
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 获取用户id
     */
    public static Integer getUserId(String token){
        Jws<Claims> claimsJws =
                Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        return (Integer) claims.get("id");
    }

    /**
     * 获取用户type
     */
    public static Integer getUserType(String token){
        Jws<Claims> claimsJws =
                Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        return (Integer) claims.get("type");
    }

    /**
     * 根据token获取xm
     * @return
     */
    public static String getMemberIdByJwtToken(String token) {
//        String jwtToken = request.getHeader("token");
        Jws<Claims> claimsJws =
                Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        return (String)claims.get("username");
    }




    public static void main(String[] args) {
//       生成token
//        UserInfo info = new UserInfo();
//        info.setId(1);
//        info.setUsername("admin");
//        info.setType(1);// 1代表管理员
//        String token = getJwtToken(info);
//        System.out.println(token);
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2Njc5ODMzNTYsImV4cCI6MTY2Nzk4NTE1NiwidXNlcm5hbWUiOiJhZG1pbiIsInR5cGUiOjEsImlkIjoxfQ.HcPk6jfl3WiwkRDAulyyjTFZzrqcr2nDiHeq3KyuWVg";
        boolean b = checkToken(token);
        System.out.println(b);
        System.out.println(getMemberIdByJwtToken(token));
    }

}
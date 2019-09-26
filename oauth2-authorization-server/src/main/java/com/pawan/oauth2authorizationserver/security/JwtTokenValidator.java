package com.pawan.oauth2authorizationserver.security;
/*package com.pawan.jsonwebtokensecurity.security;




import java.util.Arrays;
import org.springframework.stereotype.Component;
import com.pawan.jsonwebtokensecurity.model.Role;
import com.pawan.jsonwebtokensecurity.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtTokenValidator {

	private String secretKey="amazon";
	
	public User validate(String token) {
		User jwtuser=null;
		try{
		Claims body=(Claims)Jwts.parser().setSigningKey(secretKey)
				.parse(token).getBody();
		
		 System.out.println("Token body contains :: "+body);
		String subject=body.getSubject();
		 System.out.println("token subject is  :: "+ subject);
		 jwtuser = new User();
		//System.out.println("user role is :: "+jwtuser.getRoles());
		jwtuser.setUsername(subject);
		 Integer userId=(Integer)body.get("userId");
		 
		 System.out.println("userid in json parse:: "+userId);
		jwtuser.setUserId(new Long(userId));
		String jsonRole=(String)body.get("role");
		 System.out.println("Role coming from token :: "+jsonRole);
		 Role role=new Role();
		 role.setRoleName(jsonRole);
		jwtuser.setRoles(Arrays.asList(role));
		 System.out.println("user role is :: "+jwtuser.getRoles());
		
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		return jwtuser;
		
	}

}
*/
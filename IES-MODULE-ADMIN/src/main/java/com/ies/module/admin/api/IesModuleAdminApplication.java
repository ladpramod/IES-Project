package com.ies.module.admin.api;

import com.ies.module.admin.api.constants.AppConstants;
import com.ies.module.admin.api.entities.Role;
import com.ies.module.admin.api.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableEurekaClient

public class IesModuleAdminApplication  {

	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(IesModuleAdminApplication.class, args);
	}


	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

//	@Override
//	public void run(String... args) throws Exception {
//		try {
//			Role role = new Role();
//			role.setRoleId(AppConstants.ROLE_ADMIN);
//			role.setRoleName("ROLE_ADMIN");
//
//			Role role1 = new Role();
//			role1.setRoleId(AppConstants.ROLE_USER);
//			role1.setRoleName("ROLE_USER");
//
//			List<Role> roles = Arrays.asList(role,role1);
//			List<Role> roleList = this.roleRepository.saveAll(roles);
//			roleList.stream().forEach(r -> {System.out.println(r.getRoleName());});
//		}
//		catch (Exception e){
//			//TODO: Exception not handled
//		}
//	}

}

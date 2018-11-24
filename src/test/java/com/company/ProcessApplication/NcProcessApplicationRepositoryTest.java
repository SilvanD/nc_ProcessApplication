package com.company.ProcessApplication;

        import static org.assertj.core.api.Assertions.assertThat;

        import com.company.ProcessApplication.Model.*;
        import com.company.ProcessApplication.Repository.*;
        import org.junit.Before;
        import org.junit.Test;
        import org.junit.runner.RunWith;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
        import org.springframework.test.context.junit4.SpringRunner;

        import java.io.IOException;
        import java.time.LocalDate;

@RunWith(SpringRunner.class)
@DataJpaTest
public class NcProcessApplicationRepositoryTest {

    @Autowired
    private RoleDAO roleDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private SpecificationDAO specificationDAO;
    @Autowired
    private OrderDAO orderDAO;
    @Autowired
    private ServiceDAO serviceDAO;

    private User admin;

    private Role testRole;

    private Specification internet100;

    private Order testOrder;

    private Service testService;


    @Before
    public void setUp() {
        admin = new User("admin", "someadminpass");
        testRole = new Role(admin, "ROLE_ADMIN");
        internet100 = new Specification(100,300);
    }

    /***********************************
     * Create tests                    *
     ***********************************/
    @Test
    public void saveUserAndCheckIdNotNull() {
        admin = userDAO.save(admin);
        assertThat(admin.getName()).isNotNull();
    }

    @Test
    public void saveRoleAndCheckIdNotNull() {
        admin = userDAO.save(admin);
        testRole = new Role(admin, "ROLE_ADMIN");
        testRole = roleDAO.save(testRole);
        assertThat(testRole.getId()).isNotNull();
    }

    @Test
    public void saveSpecAndCheckIdNotNull() {
        internet100 = specificationDAO.save(internet100);
        assertThat(internet100.getId()).isNotNull();
    }

    @Test
    public void saveServiceAndCheckIdNotNull() {
        internet100 = specificationDAO.save(internet100);
        testService = new Service(internet100,ServiceStatus.Active,LocalDate.now().minusDays(2),null);
        testService = serviceDAO.save(testService);
        assertThat(testService.getId()).isNotNull();
    }

    @Test
    public void saveOrderAndCheckIdNotNull() throws IOException {
        admin = userDAO.save(admin);
        internet100 = specificationDAO.save(internet100);
        testOrder = new Order(internet100, admin, OrderType.New);
        testOrder = orderDAO.save(testOrder);
        System.out.print(testOrder.getId());
        assertThat(testOrder.getId()).isNotNull();
    }

    /********************************
     * Retrieve tests               *
     ********************************/
    @Test
    public void saveUserAndFindById() {
        admin = userDAO.save(admin);

        User output=userDAO.findById(admin.getName()).orElse(null);
        assertThat(output).isEqualTo(admin);
    }

    @Test
    public void saveRoleAndFindById() {
        admin = userDAO.save(admin);
        testRole = new Role(admin, "ROLE_ADMIN");
        testRole = roleDAO.save(testRole);

        Role output = roleDAO.findById(testRole.getId()).orElse(null);
        assertThat(output).isEqualTo(testRole);
    }

    @Test
    public void saveSpecAndFindById() {
        internet100 = specificationDAO.save(internet100);

        Specification output = specificationDAO.findById(internet100.getId()).orElse(null);
        assertThat(output).isEqualTo(internet100);
    }

    @Test
    public void saveOrderAndFindById() {
        admin = userDAO.save(admin);
        internet100 = specificationDAO.save(internet100);
        testOrder = new Order(internet100, admin, OrderType.New);
        testOrder = orderDAO.save(testOrder);

        Order output = orderDAO.findById(testOrder.getId()).orElse(null);
        assertThat(output).isEqualTo(testOrder);
    }

    @Test
    public void saveServiceAndFindById() {
        internet100 = specificationDAO.save(internet100);
        testService = new Service(internet100,ServiceStatus.Active,LocalDate.now().minusDays(2),null);
        testService = serviceDAO.save(testService);

        Service output = serviceDAO.findById(testService.getId()).orElse(null);
        assertThat(output).isEqualTo(testService);
    }


    /********************************
     * Update tests                 *
     ********************************/
    @Test
    public void getUserFromRepoAndUpdate() {
        admin = userDAO.save(admin);
        User repoUser = userDAO.findById(admin.getName()).orElse(null);
        repoUser.setPass("qwerty");

        User output = userDAO.findById(admin.getName()).orElse(null);
        assertThat(output.getName()).isEqualTo(admin.getName());
        assertThat(output.getPass()).isEqualTo(repoUser.getPass());
    }

    @Test
    public void getRoleFromRepoAndUpdate() {
        admin = userDAO.save(admin);
        testRole = new Role(admin, "ROLE_ADMIN");
        testRole = roleDAO.save(testRole);
        Role repoRole = roleDAO.findById(testRole.getId()).orElse(null);
        repoRole.setRolename("ROLE_MANAGER");

        Role output = roleDAO.findById(testRole.getId()).orElse(null);
        assertThat(output.getUser()).isEqualTo(testRole.getUser());
        assertThat(output.getRolename()).isEqualTo(repoRole.getRolename());
    }

    @Test
    public void getSpecFromRepoAndUpdate() {
        internet100 = specificationDAO.save(internet100);
        Specification repoSpec = specificationDAO.findById(internet100.getId()).orElse(null);
        repoSpec.setSpeed(new Long(88));

        Specification output = specificationDAO.findById(internet100.getId()).orElse(null);
        assertThat(output.getCost()).isEqualTo(internet100.getCost());
        assertThat(output.getSpeed()).isEqualTo(new Long(88));
    }

    @Test
    public void getOrderFromRepoAndUpdate() {
        internet100 = specificationDAO.save(internet100);
        admin = userDAO.save(admin);
        testOrder = new Order(internet100, admin, OrderType.New, OrderStatus.Entering, LocalDate.now().plusDays(2));
        testOrder = orderDAO.save(testOrder);
        Order repoOrder = orderDAO.findById(testOrder.getId()).orElse(null);
        repoOrder.setStatus(OrderStatus.Completed);

        Order output = orderDAO.findById(testOrder.getId()).orElse(null);
        assertThat(output.getType()).isEqualTo(testOrder.getType());
        assertThat(output.getStatus()).isEqualTo(repoOrder.getStatus());
    }

    @Test
    public void getServiceFromRepoAndUpdate() {
        internet100 = specificationDAO.save(internet100);
        testService = new Service(internet100, ServiceStatus.Active, LocalDate.now().minusDays(2),null);
        testService = serviceDAO.save(testService);
        Service repoService = serviceDAO.findById(testService.getId()).orElse(null);
        repoService.setStatus(ServiceStatus.Disconnected);

        Service output = serviceDAO.findById(testService.getId()).orElse(null);
        assertThat(output.getStartDate()).isEqualTo(testService.getStartDate());
        assertThat(output.getStatus()).isEqualTo(repoService.getStatus());
    }


    /********************************
     * Remove tests                 *
     ********************************/
    @Test
    public void removeUserFromRepo() {
        admin = userDAO.save(admin);
        User retrieveOutput = userDAO.findById(admin.getName()).orElse(null);

        assertThat(retrieveOutput).isEqualTo(admin);
        userDAO.delete(admin);
        User removeOutput = userDAO.findById(admin.getName()).orElse(null);
        assertThat(removeOutput).isNull();
    }

    @Test
    public void removeRoleFromRepo() {
        admin = userDAO.save(admin);
        testRole = new Role(admin, "ROLE_ADMIN");
        testRole = roleDAO.save(testRole);
        Role retrieveOutput = roleDAO.findById(testRole.getId()).orElse(null);

        assertThat(retrieveOutput).isEqualTo(testRole);
        roleDAO.delete(retrieveOutput);
        Role removeOutput = roleDAO.findById(testRole.getId()).orElse(null);
        assertThat(removeOutput).isNull();
    }

    @Test
    public void removeSpecFromRepo() {
        internet100 = specificationDAO.save(internet100);

        Specification retrieveOutput = specificationDAO.findById(internet100.getId()).orElse(null);
        assertThat(retrieveOutput).isEqualTo(internet100);
        specificationDAO.delete(retrieveOutput);
        Specification removeOutput = specificationDAO.findById(internet100.getId()).orElse(null);
        assertThat(removeOutput).isNull();
    }

    @Test
    public void removeOrderFromRepo() {
        admin = userDAO.save(admin);
        internet100 = specificationDAO.save(internet100);
        testOrder = new Order(internet100, admin, OrderType.New);
        testOrder = orderDAO.save(testOrder);

        Order retrieveOutput = orderDAO.findById(testOrder.getId()).orElse(null);
        assertThat(retrieveOutput).isEqualTo(testOrder);
        orderDAO.delete(retrieveOutput);
        Order removeOutput = orderDAO.findById(testOrder.getId()).orElse(null);
        assertThat(removeOutput).isNull();
    }

    @Test
    public void removeServiceFromRepo() {
        internet100 = specificationDAO.save(internet100);
        testService = new Service(internet100,ServiceStatus.Active,LocalDate.now().minusDays(2),null);
        testService = serviceDAO.save(testService);

        Service retrieveOutput = serviceDAO.findById(testService.getId()).orElse(null);
        assertThat(retrieveOutput).isEqualTo(testService);
        serviceDAO.delete(retrieveOutput);
        Service removeOutput = serviceDAO.findById(testService.getId()).orElse(null);
        assertThat(removeOutput).isNull();
    }
}

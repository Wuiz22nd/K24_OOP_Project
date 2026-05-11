    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package OderServiceTest;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import model.MilkTea;
import model.Tea;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import service.OrderService;

/**
 *
 * @author wuiz
 */
public class OrderServiceTest {

    @Test
    public void testProcessOrderSuccess() {

        OrderService service = new OrderService();

        Tea tea = new MilkTea();

        assertDoesNotThrow(() -> {
            service.processOrder(tea);
        });

        assertNotNull(service.getCurrentOrder());

        assertEquals(
                1,
                service.getCurrentOrder().getDrinks().size()
        );
    }

    @Test
    public void testResetInventory() {

        OrderService service = new OrderService();

        service.resetInventory();

        assertNotNull(service.getCurrentOrder());
    }
}

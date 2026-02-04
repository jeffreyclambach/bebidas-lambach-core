package com.bebidas.test;

import com.bebidas.service.OrderService;
import org.junit.Test;
import static org.junit.Assert.*;

public class OrderServiceTest {

    @Test
    public void testCalcularTotal() {

        OrderService service = new OrderService();

        double resultado = service.calcularTotal(10.0, 3);

        assertEquals(30.0, resultado, 0.001);
    }
}

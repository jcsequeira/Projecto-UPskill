package model;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class AdministradorTest {
    @Test
    public void test_class_can_be_instantiated() {
        Administrador administrador = new Administrador();
        assertNotNull(administrador);
    }
}
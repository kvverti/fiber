package me.zeroeightsix.fiber.annotation;

import me.zeroeightsix.fiber.annotation.constraint.Range;
import me.zeroeightsix.fiber.annotation.exception.MalformedFieldException;
import me.zeroeightsix.fiber.exception.FiberException;
import me.zeroeightsix.fiber.tree.ConfigNode;
import me.zeroeightsix.fiber.tree.ConfigValue;
import me.zeroeightsix.fiber.tree.Property;
import me.zeroeightsix.fiber.tree.TreeItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.function.BiConsumer;

import static org.junit.jupiter.api.Assertions.*;

class AnnotatedSettingsTest {

    private ConfigNode node;

    @BeforeEach
    void setup() {
        node = new ConfigNode();
    }

    @Test
    @DisplayName("Convert POJO to IR")
    void testPojoIR() throws FiberException {
        OneFieldPojo pojo = new OneFieldPojo();
        AnnotatedSettings.applyToNode(node, pojo);

        Set<TreeItem> items = node.getItems();
        assertEquals(1, items.size(), "Setting map is 1 entry large");
        TreeItem item = node.lookup("a");
        assertNotNull(item, "Setting exists");
        assertTrue(ConfigValue.class.isAssignableFrom(item.getClass()), "Setting is a ConfigValue");
        ConfigValue<?> configValue = (ConfigValue<?>) item;
        assertNotNull(configValue.getValue(), "Setting value is non-null");
        assertEquals(Integer.class, configValue.getType(), "Setting type is correct");
        assertEquals(Integer.class, configValue.getValue().getClass(), "Setting value reflects correct type");
        Integer integer = (Integer) configValue.getValue();
        assertEquals(integer, 5, "Setting value is correct");
    }

    @Test
    @DisplayName("Throw no final exception")
    void testNoFinal() {
        NoFinalPojo pojo = new NoFinalPojo();
        assertThrows(FiberException.class, () -> AnnotatedSettings.applyToNode(node, pojo));
    }

    @Test
    @DisplayName("Listener")
    void testListener() throws FiberException {
        ListenerPojo pojo = new ListenerPojo();
        AnnotatedSettings.applyToNode(node, pojo);

        TreeItem treeItem = node.lookup("a");
        assertNotNull(treeItem, "Setting exists");
        assertTrue(treeItem instanceof Property<?>, "Setting is a property");
        @SuppressWarnings("unchecked")
        Property<Integer> property = (Property<Integer>) treeItem;
        property.setValue(10);
        assertEquals(true, pojo.listened, "Listener was triggered");
    }

    @Test
    @DisplayName("Listener with different generics")
    void testTwoGenerics() {
        NonMatchingListenerPojo pojo = new NonMatchingListenerPojo();
        assertThrows(FiberException.class, () -> AnnotatedSettings.applyToNode(node, pojo));
    }

    @Test
    @DisplayName("Listener with wrong generic type")
    void testWrongGenerics() {
        WrongGenericListenerPojo pojo = new WrongGenericListenerPojo();
        assertThrows(FiberException.class, () -> AnnotatedSettings.applyToNode(node, pojo));
    }

    @Test
    @DisplayName("Numerical constraints")
    void testNumericalConstraints() throws FiberException {
        NumericalConstraintsPojo pojo = new NumericalConstraintsPojo();
        AnnotatedSettings.applyToNode(node, pojo);
        @SuppressWarnings("unchecked")
        Property<Integer> value = (Property<Integer>) node.lookup("a");
        assertNotNull(value, "Setting exists");
        assertEquals(false, value.setValue(-10));
        assertEquals(true, value.setValue(5));
        assertEquals(false, value.setValue(20));
    }

    @Test
    @DisplayName("Invalid numerical constraints")
    void testInvalidNumericalConstraints() throws FiberException {
        InvalidNumericalConstraintsPojo pojo = new InvalidNumericalConstraintsPojo();
        assertThrows(MalformedFieldException.class, () -> AnnotatedSettings.applyToNode(node, pojo));
    }

    @Test
    @DisplayName("Invalid constraint type")
    void testInvalidConstraintType() throws FiberException {
        InvalidValueTypePojo pojo = new InvalidValueTypePojo();
        assertThrows(MalformedFieldException.class, () -> AnnotatedSettings.applyToNode(node, pojo));
    }

    private static class NoFinalPojo {
        private int a = 5;
    }

    private static class OneFieldPojo {
        private final int a = 5;
    }

    private static class ListenerPojo {
        @Setting.Ignored
        private boolean listened = false;

        private final int a = 5;

        @Listener("a")
        private final BiConsumer<Integer, Integer> aListener = (now, then) -> listened = true;
    }

    private static class NonMatchingListenerPojo {
        private final int a = 5;

        @Listener("a")
        private final BiConsumer<Double, Integer> aListener = (now, then) -> {};
    }

    private static class WrongGenericListenerPojo {
        private final int a = 5;

        @Listener("a")
        private final BiConsumer<Double, Double> aListener = (now, then) -> {};
    }

    private static class NumericalConstraintsPojo {
        @Range(min = 0, max = 10)
        private final int a = 5;
    }

    private static class InvalidNumericalConstraintsPojo {
        @Range(min = 3, max = 0)
        private final int a = 5;
    }

    private static class InvalidValueTypePojo {
        @Range(min = 0)
        private final String a = "";
    }

}

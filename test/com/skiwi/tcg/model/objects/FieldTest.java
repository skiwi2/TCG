
package com.skiwi.tcg.model.objects;

import com.skiwi.tcg.model.cards.MonsterCard;
import java.util.List;
import java.util.stream.Collectors;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Frank van Heeswijk
 */
public class FieldTest {
    static {
        assertTrue(true);
    }

    @Test
    public void testConstructor() {
        new Field(1);
        new Field(6);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorZeroMonsterCapacity() {
        new Field(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNegativeMonsterCapacity() {
        new Field(-1);
    }

    @Test
    public void testSetMonster() {
        Field field = new Field(6);
        MonsterCard monsterCard = createMonsterCard();
        field.setMonster(0, monsterCard);
        assertEquals(monsterCard, field.getMonster(0));

        MonsterCard monsterCard2 = createMonsterCard2();
        field.setMonster(5, monsterCard2);
        assertEquals(monsterCard2, field.getMonster(5));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetMonsterUnderRange() {
        Field field = new Field(6);
        field.setMonster(-1, createMonsterCard());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testSetMonsterAboveRange() {
        Field field = new Field(6);
        field.setMonster(6, createMonsterCard());
    }

    @Test(expected = NullPointerException.class)
    public void testSetMonsterMonsterCardNull() {
        Field field = new Field(6);
        field.setMonster(0, null);
    }
    
    @Test(expected = IllegalStateException.class)
    public void testSetMonsterMonsterAlreadyExists() {
        Field field = new Field(1);
        MonsterCard monsterCard = createMonsterCard();
        MonsterCard monsterCard2 = createMonsterCard2();
        field.setMonster(0, monsterCard);
        field.setMonster(0, monsterCard2);
    }

    @Test
    public void testGetMonster() {
        Field field = new Field(6);
        MonsterCard monsterCard = createMonsterCard();
        field.setMonster(0, monsterCard);
        assertEquals(monsterCard, field.getMonster(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetMonsterUnderRange() {
        Field field = new Field(6);
        field.getMonster(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetMonsterAboveRange() {
        Field field = new Field(6);
        field.getMonster(6);
    }

    @Test(expected = IllegalStateException.class)
    public void testGetMonsterNoMonster() {
        Field field = new Field(6);
        field.getMonster(0);
    }

    @Test
    public void testHasMonster() {
        Field field = new Field(6);
        field.setMonster(0, createMonsterCard());
        assertTrue(field.hasMonster(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testHasMonsterUnderRange() {
        Field field = new Field(6);
        field.hasMonster(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testHasMonsterAboveRange() {
        Field field = new Field(6);
        field.hasMonster(6);
    }

    @Test
    public void testDestroyMonster() {
        Field field = new Field(6);
        MonsterCard monsterCard = createMonsterCard();
        field.setMonster(0, monsterCard);
        assertEquals(monsterCard, field.destroyMonster(0));
        assertFalse(field.hasMonster(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testDestroyMonsterUnderRange() {
        Field field = new Field(6);
        field.destroyMonster(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testDestroyMonsterAboveRange() {
        Field field = new Field(6);
        field.destroyMonster(6);
    }

    @Test(expected = IllegalStateException.class)
    public void testDestroyMonsterNoMonster() {
        Field field = new Field(6);
        field.destroyMonster(0);
    }

    @Test
    public void testGetMonsters() {
        Field field = new Field(6);
        assertEquals(0, field.getMonsters().count());
        assertFalse(field.getMonsters().anyMatch(m -> m == null));

        MonsterCard monsterCard = createMonsterCard();
        MonsterCard monsterCard2 = createMonsterCard2();
        field.setMonster(0, monsterCard);
        field.setMonster(1, monsterCard2);
        List<MonsterCard> list = field.getMonsters().collect(Collectors.toList());
        assertTrue(list.contains(monsterCard));
        assertTrue(list.contains(monsterCard2));
    }

    @Test
    public void testGetMonsterCapacity() {
        Field field = new Field(6);
        assertEquals(6, field.getMonsterCapacity());
    }

    @Test
    public void testToString() {
        Field field = new Field(5);
        MonsterCard monsterCard = createMonsterCard();
        MonsterCard monsterCard2 = createMonsterCard2();
        field.setMonster(1, monsterCard);
        field.setMonster(3, monsterCard2);
        assertEquals(Field.class.getSimpleName() + "([null, " + monsterCard.toString() + ", null, " + monsterCard2.toString() + ", null])", field.toString());
    }

    private MonsterCard createMonsterCard() {
        return new MonsterCard("Test", 5, 5, MonsterModus.HEALING);
    }

    private MonsterCard createMonsterCard2() {
        return new MonsterCard("zzz", 7, 7, MonsterModus.OFFENSIVE);
    }
}
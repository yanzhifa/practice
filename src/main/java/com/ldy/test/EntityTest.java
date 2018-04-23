package com.ldy.test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by yanz3 on 9/6/16.
 */
public class EntityTest {

    public void removeEntity(List<Entity> entitys) {
        List<Entity> entityList =
                entitys.stream().filter(
                        item -> item.getId().equals("1")).collect(Collectors.toList());
        entitys.removeAll(entityList);
    }

    public static void main(String[] args) {
        EntityTest t = new EntityTest();
        Entity e1 = t.new Entity("1", 1);
        Entity e2 = t.new Entity("2", 2);
        List<Entity> list = new ArrayList<>();
        list.add(e1);
        list.add(e2);
        System.out.println(list.size());
        t.removeEntity(list);
        System.out.println(list.size());

        String a = null;
        String b = null;
        System.out.println(a.equals(b));
    }

    class Entity {
        String id;
        Integer number;

        public Entity(String id, Integer number) {
            this.id = id;
            this.number = number;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Integer getNumber() {
            return number;
        }

        public void setNumber(Integer number) {
            this.number = number;
        }
    }
}

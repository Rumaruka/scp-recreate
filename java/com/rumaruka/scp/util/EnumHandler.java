package com.rumaruka.scp.util;

import com.rumaruka.scp.client.creativetabs.SCPTab;
import net.minecraft.util.IStringSerializable;

public class EnumHandler {




    public static enum SCPType implements IStringSerializable{
        SAFE("safe",0),
        EUCLID("euclid",1),
        KETER("keter",2),
        THAUMIEL("thaumiel",3);
        private int ID;
        private String name;
        private SCPType(String name, int id){
            this.ID=id;
            this.name=name;

        }

        @Override
        public String getName() {
            return this.name;
        }

        public int getID() {
            return ID;
        }

        @Override
        public String toString() {
            return getName();
        }
    }

    public static enum KeyCardType implements IStringSerializable{
        I("i",0),
        II("ii",1),
        III("iii",2),
        OMNI("omni",3),
        ;
        private int ID;
        private String name;
        private KeyCardType(String name, int id){
            this.ID=id;
            this.name=name;

        }

        @Override
        public String getName() {
            return this.name;
        }

        public int getID() {
            return ID;
        }

        @Override
        public String toString() {
            return getName();
        }
    }
}

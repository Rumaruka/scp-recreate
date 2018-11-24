package com.rumaruka.scp.client.gui;

import com.rumaruka.scp.common.config.SCPConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.fml.client.IModGuiFactory;
import net.minecraftforge.fml.client.config.DummyConfigElement;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.GuiConfigEntries;
import net.minecraftforge.fml.client.config.IConfigElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class SCPGuiFactory implements IModGuiFactory {
    @Override
    public void initialize(Minecraft minecraftInstance) {

    }

    @Override
    public boolean hasConfigGui() {
        return false;
    }
public Class<? extends GuiScreen> mainConfigGuiClass(){
        return SCPConfigGui.class;
}
    @Override
    public GuiScreen createConfigGui(GuiScreen parentScreen) {
        return null;
    }

    @Override
    public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
        return null;
    }

    public static class SCPConfigGui extends GuiConfig{

        public SCPConfigGui(GuiScreen parent) {
            super((GuiScreen) getConfigElements(), "scp", false, false, I18n.format("scp.configgui.name", new Object[0]));

        }

        private static List<IConfigElement> getConfigElements(){
            List<IConfigElement> list = new ArrayList<>();
            list.add(new DummyConfigElement.DummyCategoryElement("blink","scp.cfg.blink.title",BlinkEntry.class));
            return list;
        }
    }

    private static class BlinkEntry extends GuiConfigEntries.CategoryEntry {
        public BlinkEntry(GuiConfig owningScreen, GuiConfigEntries owningEntryList, IConfigElement configElement) {
            super(owningScreen, owningEntryList, configElement);
        }
        protected GuiScreen buildChildScreen(){
              return new GuiConfig(this.owningScreen, new ConfigElement(SCPConfig.getConfig().getCategory("blink")).getChildElements(), "scp", "blink", true, false, "scp.blink.title", GuiConfig.getAbridgedConfigPath(SCPConfig.getConfig().toString()));

        }
    }
}

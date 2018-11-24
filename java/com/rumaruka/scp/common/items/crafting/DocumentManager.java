package com.rumaruka.scp.common.items.crafting;

import com.rumaruka.scp.common.registry.DocumentRegistry;
import com.rumaruka.scp.init.SCPItem;
import com.rumaruka.scp.util.RecipesUtil;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class DocumentManager {

    private static final DocumentManager instance = new DocumentManager();



    private DocumentManager(){
        for(int i = 0; i < DocumentRegistry.scpList.size();i++){
            RecipesUtil.addShapelessRecipe(new ItemStack(SCPItem.document,1,i),new Object[]{Items.PAPER
            });
        }
    }

    public static DocumentManager getInstance(){
        return instance;
    }


}

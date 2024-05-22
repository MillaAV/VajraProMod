package millaav.vajrapro.client.gui;

import millaav.vajrapro.ItemVajra;
import millaav.vajrapro.common.container.ContainerToolStation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;

public class GuiToolStation extends GuiContainer {
    public GuiToolStation(Container container){
        super(container);
        xSize = 176;
        ySize = 188;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        ResourceLocation resLoc = new ResourceLocation("vajrapro", "textures/gui/background.png");
        Minecraft.getMinecraft().getTextureManager().bindTexture(resLoc);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, 256, 256);

        ResourceLocation progressArrowResLoc = new ResourceLocation("vajrapro","textures/gui/progress_arrow.png");
        Minecraft.getMinecraft().getTextureManager().bindTexture(progressArrowResLoc);
        drawTexturedModalRect(guiLeft+80, guiTop+40, 0 ,0, 15, 22);


        ResourceLocation resLoc1 = new ResourceLocation("vajrapro","textures/gui/vajra_slot.png");
        Minecraft.getMinecraft().getTextureManager().bindTexture(resLoc1);
        drawTexturedModalRect(guiLeft+79, guiTop+20, 0 ,0, 18, 18);

        ResourceLocation info = new ResourceLocation("vajrapro","textures/gui/infobutton.png");
        Minecraft.getMinecraft().getTextureManager().bindTexture(info);
        drawTexturedModalRect(guiLeft+3, guiTop+3, 0 ,0, 10, 10);

        ResourceLocation resLoc4 = new ResourceLocation("vajrapro","textures/gui/upgrade_slot.png");
        Minecraft.getMinecraft().getTextureManager().bindTexture(resLoc4);
        drawTexturedModalRect(guiLeft+79, guiTop+70, 0 ,0, 18, 18);

        int strLen = Minecraft.getMinecraft().fontRenderer.getStringWidth(StatCollector.translateToLocalFormatted("block.guitoolstation.name"));
        fontRendererObj.drawString(StatCollector.translateToLocal("block.guitoolstation.name"),guiLeft+176/2 - strLen/2, guiTop+6,0x000000);
        if (mouseX >= guiLeft+3 && mouseX <= guiLeft + 12 && mouseY >= guiTop+3 && mouseY <= guiTop + 12) {
            ArrayList<String> desc = new ArrayList<>();
            desc.add(StatCollector.translateToLocal("block.guitoolstation.skto"));
            desc.add(StatCollector.translateToLocal("block.guitoolstation.with"));
            drawHoveringText(desc,mouseX,mouseY, fontRendererObj);
        }
        GL11.glColor4f(1,1,1,1);
        RenderHelper.disableStandardItemLighting();
        GL11.glDisable(GL11.GL_LIGHTING);

        ItemStack vajraSlotStack = ((ContainerToolStation)inventorySlots).vajraSlot.getStack();
        if(vajraSlotStack == null) return;

        ResourceLocation resLoc2 = new ResourceLocation("vajrapro","textures/gui/automelting_slot.png");
        Minecraft.getMinecraft().getTextureManager().bindTexture(resLoc2);
        drawTexturedModalRect(guiLeft+52, guiTop+70, 0 ,0, 18, 18);

        ResourceLocation resLoc3 = new ResourceLocation("vajrapro","textures/gui/silktouch_slot.png");
        Minecraft.getMinecraft().getTextureManager().bindTexture(resLoc3);
        drawTexturedModalRect(guiLeft+106, guiTop+70, 0 ,0, 18, 18);

        double progress = ((ContainerToolStation)inventorySlots).tile.upgradingProgress/200d;
        int topPos = 22 - (int) (progress*22d);
        int progressHeight = (int) (progress*22d);
        Minecraft.getMinecraft().getTextureManager().bindTexture(progressArrowResLoc);
        drawTexturedModalRect(guiLeft+80, guiTop+40+topPos, 16 , topPos, 15, progressHeight);

        resLoc = new ResourceLocation("vajrapro", "textures/gui/background.png");
        Minecraft.getMinecraft().getTextureManager().bindTexture(resLoc);

        int tier = ((ItemVajra)vajraSlotStack.getItem()).getVajraTier();

        if(tier>0) {
            drawTexturedModalRect(guiLeft + 25, guiTop + 20, 7, 105, 18, 18);
            drawTexturedModalRect(guiLeft + 133, guiTop + 20, 7, 105, 18, 18);
        }
        if(tier>1) {
            drawTexturedModalRect(guiLeft + 25, guiTop + 45, 7, 105, 18, 18);
            drawTexturedModalRect(guiLeft + 133, guiTop + 45, 7, 105, 18, 18);
        }
        if(tier>2) {
            drawTexturedModalRect(guiLeft + 25, guiTop + 70, 7, 105, 18, 18);
            drawTexturedModalRect(guiLeft + 133, guiTop + 70, 7, 105, 18, 18);
        }

    }
}

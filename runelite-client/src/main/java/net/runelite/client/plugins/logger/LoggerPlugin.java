package net.runelite.client.plugins.logger;

import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.*;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.input.KeyManager;
import net.runelite.client.input.MouseManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.plugins.logger.listeners.*;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.stream.Collectors;

@PluginDescriptor(
        name = "Logger",
        description = "Logs every action and event in the game"
)
@Slf4j
public class LoggerPlugin extends Plugin {

    @Inject
    private Client client;

    @Inject
    private KeyManager keyManager;

    @Inject
    private MouseManager mouseManager;

    private final LoggerKeyListener keyListener = new LoggerKeyListener();
    private final LoggerMouseListener mouseListener = new LoggerMouseListener();

    @Override
    protected void startUp() throws Exception {
        this.keyManager.registerKeyListener(this.keyListener);
        this.mouseManager.registerMouseListener(this.mouseListener);
        super.startUp();
    }

    @Override
    protected void shutDown() throws Exception {
        this.keyManager.unregisterKeyListener(this.keyListener);
        this.mouseManager.unregisterMouseListener(this.mouseListener);
        super.shutDown();
    }



    @Subscribe
    public void onFocusChanged(FocusChanged event) {
        if (event.isFocused()) {
            Logger.write(LogEntry.FOCUS_GAINED);
        } else {
            Logger.write(LogEntry.FOCUS_LOST);
        }
    }

    /* Player */

    @Subscribe
    public void onPlayerMoved(PlayerMoved e) {

    }

    @Subscribe
    public void onPlayerSpawned(PlayerSpawned e) {
        final Player player = e.getPlayer();
        final LocalPoint p = player.getLocalLocation();
        Logger.write(LogEntry.PLAYER_SPAWNED, player.getName(), player.getAnimation(),
                p.getX(), p.getY(),
                p.getSceneX(), p.getSceneY());
    }

    @Subscribe
    public void onPlayerDespawned(PlayerDespawned e) {
        final Player player = e.getPlayer();
        Logger.write(LogEntry.PLAYER_DESPAWNED, player.getName(), player.getAnimation(),
                player.getLocalLocation().getX(), player.getLocalLocation().getY(),
                player.getLocalLocation().getSceneX(), player.getLocalLocation().getSceneY());
    }

    /* Npc */

    @Subscribe
    public void onNpcSpawned(NpcSpawned e) {
        final NPC npc = e.getNpc();
        Logger.write(LogEntry.NPC_SPAWNED, npc.getName(), npc.getId(),
                npc.getLocalLocation().getX(), npc.getLocalLocation().getY(),
                npc.getLocalLocation().getSceneX(), npc.getLocalLocation().getSceneY());
    }

    @Subscribe
    public void onNpcDespawned(NpcDespawned e) {
        final NPC npc = e.getNpc();
        Logger.write(LogEntry.NPC_DESPAWNED, npc.getName(), npc.getId(),
                npc.getLocalLocation().getX(), npc.getLocalLocation().getY(),
                npc.getLocalLocation().getSceneX(), npc.getLocalLocation().getSceneY());
    }

    /* Actor */

    @Subscribe
    public void onAnimationChanged(AnimationChanged e) {
        final Actor actor = e.getActor();
        if (actor instanceof Player) {
            Logger.write(LogEntry.PLAYER_ANIMATION_CHANGED, actor.getName(), actor.getAnimation());
        } else if (actor instanceof NPC) {
            Logger.write(LogEntry.NPC_ANIMATION_CHANGED, actor.getName(), ((NPC) actor).getId(), actor.getAnimation());
        }
    }

    /* GroundItem */

    @Subscribe
    public void onItemSpawned(ItemSpawned e) {
        Logger.write(LogEntry.GROUND_ITEM_SPAWNED, e.getItem().getId(), e.getItem().getQuantity(),
                e.getTile().getLocalLocation().getX(), e.getTile().getLocalLocation().getY(),
                e.getTile().getLocalLocation().getSceneX(), e.getTile().getLocalLocation().getSceneY());
    }

    @Subscribe
    public void onItemDespawned(ItemDespawned e) {
        Logger.write(LogEntry.GROUND_ITEM_DESPAWNED, e.getItem().getId(), e.getItem().getQuantity(),
                e.getTile().getLocalLocation().getX(), e.getTile().getLocalLocation().getY(),
                e.getTile().getLocalLocation().getSceneX(), e.getTile().getLocalLocation().getSceneY());
    }

    /* Objects */

    @Subscribe
    public void onGameObjectSpawned(GameObjectSpawned e) {
        final GameObject o = e.getGameObject();
        final LocalPoint p = o.getLocalLocation();
        Logger.write(LogEntry.GAMEOBJECT_SPAWNED, o.getId(), p.getX(), p.getY(), p.getSceneX(), p.getSceneY());
    }

    @Subscribe
    public void onGameObjectDespawned(GameObjectDespawned e) {
        final GameObject o = e.getGameObject();
        final LocalPoint p = o.getLocalLocation();
        Logger.write(LogEntry.GAMEOBJECT_SPAWNED, o.getId(), p.getX(), p.getY(), p.getSceneX(), p.getSceneY());
    }

    /* Inventory */

    @Subscribe
    public void onItemContainerChanged(ItemContainerChanged e) {
        Logger.write(LogEntry.ITEM_CONTAINER_CHANGED, e.getContainerId(), Arrays.stream(e.getItemContainer().getItems())
                .map(Item::getId).collect(Collectors.toList()));
        Logger.write(LogEntry.ITEM_CONTAINER_CHANGED, e.getContainerId(), Arrays.stream(e.getItemContainer().getItems())
                .map(Item::getQuantity).collect(Collectors.toList()));
    }

    /* Widget */

    @Subscribe
    public void onWidgetLoaded(WidgetLoaded e) {
        Logger.write(LogEntry.WIDGET_LOADED, e.getGroupId());

    }

    /* Interaction */

    @Subscribe
    public void onMenuOptionClicked(MenuOptionClicked e) {
        Logger.write(LogEntry.MENU_ACTION_CLICKED, e.getActionParam(), e.getMenuOption(), e.getMenuTarget(),
                e.getMenuAction().getId(), e.getId(), e.getWidgetId(), e.isConsumed());
    }

}

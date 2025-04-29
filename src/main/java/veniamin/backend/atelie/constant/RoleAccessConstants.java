package veniamin.backend.atelie.constant;

import veniamin.backend.atelie.entity.Role;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class RoleAccessConstants {

    public static final Map<Role, Map<String, Set<String>>> PERMISSIONS = Map.of(
            Role.ROLE_CLIENT, Map.of(
                    "SELECT", Set.of("fabric", "product", "fabric_type", "complication")
            ),
            Role.ROLE_CUTTER, Map.of(
                    "SELECT", Set.of("fabric", "product", "fabric_type", "complication"),
                    "INSERT", Set.of("fabric", "product", "fabric_type", "complication"),
                    "UPDATE", Set.of("fabric", "product", "fabric_type", "complication"),
                    "DELETE", Set.of("fabric", "product", "fabric_type", "complication")
            ),
            Role.ROLE_MANAGER, Map.of(
                    "SELECT", Set.of("cutter_category", "cutter", "fabric_type", "product", "fabric", "complication", "order_", "complication_order", "fabric_order", "cost_order", "cash"),
                    "INSERT", Set.of("cutter_category", "cutter", "fabric_type", "product", "fabric", "complication", "order_", "complication_order", "fabric_order", "cost_order", "cash"),
                    "UPDATE", Set.of("cutter_category", "cutter", "fabric_type", "product", "fabric", "complication", "order_", "complication_order", "fabric_order", "cost_order", "cash"),
                    "DELETE", Set.of("cutter_category", "cutter", "fabric_type", "product", "fabric", "complication", "order_", "complication_order", "fabric_order", "cost_order", "cash")
            )
    );


    // Действия доступные только к данным связанным с пользователем
    public static final Map<Role, Map<String, Map<String, String>>> ONLY_USER_PERMISSIONS = Map.of(
            Role.ROLE_CLIENT, Map.of(
                    "SELECT", Map.of(
                            "order_", "client_name",
                            "complication_order", "client_name",
                            "fabric_order", "client_name",
                            "cost_order", "client_name")
            ),
            Role.ROLE_CUTTER, Map.of(
                    "SELECT", Map.of(
                            "order_", "cutter_id",
                            "complication_order", "cutter_id",
                            "fabric_order", "cutter_id",
                            "cost_order", "cutter_id")
            )
    );

}

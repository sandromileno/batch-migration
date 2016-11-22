package br.com.m4u.migration.reload.enums;

/**
 * Created by sandro on 07/11/16.
 */
public enum MigrationStatusEnum {
    MIGRATED("MIGRATED"),
    CHANGED("CHANGED"),
    NOT_MIGRATED("NOT_MIGRATED");

    private String status;

    MigrationStatusEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}

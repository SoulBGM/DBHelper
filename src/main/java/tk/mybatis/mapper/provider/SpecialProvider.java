//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package tk.mybatis.mapper.provider;

import java.util.Iterator;
import java.util.Set;

import git.soulbgm.common.annotation.IdBuild;
import git.soulbgm.common.enumerate.IdType;
import org.apache.ibatis.mapping.MappedStatement;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.mapperhelper.EntityHelper;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;
import tk.mybatis.mapper.mapperhelper.SqlHelper;

public class SpecialProvider extends MapperTemplate {
    public SpecialProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    public String insertList(MappedStatement ms) {
        Class<?> entityClass = this.getEntityClass(ms);

        // 更改此处 start
        boolean flag = false;
        Set<EntityColumn> columns = EntityHelper.getColumns(entityClass);
        for (EntityColumn column : columns) {
            IdBuild idBuild = column.getEntityField().getAnnotation(IdBuild.class);
            if (idBuild != null) {
                flag = idBuild.idType() == IdType.JDBC;
            }
            if (flag) {
                break;
            }
        }
        // end

        StringBuilder sql = new StringBuilder();
        sql.append(SqlHelper.insertIntoTable(entityClass, this.tableName(entityClass)));

        // 更改此处 start
        sql.append(SqlHelper.insertColumns(entityClass, flag, false, false));
        /*sql.append(SqlHelper.insertColumns(entityClass, false, false, false));*/
        // end

        sql.append(" VALUES ");
        sql.append("<foreach collection=\"list\" item=\"record\" separator=\",\" >");
        sql.append("<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">");
        Set<EntityColumn> columnList = EntityHelper.getColumns(entityClass);
        Iterator var5 = columnList.iterator();

        while (var5.hasNext()) {
            EntityColumn column = (EntityColumn) var5.next();
            // 更改此处 start
            if (flag) {
                if (!column.isId() && column.isInsertable()) {
                    sql.append(column.getColumnHolder("record") + ",");
                }
            } else {
                if (column.isInsertable()) {
                    sql.append(column.getColumnHolder("record") + ",");
                }
            }
            /*if (!column.isId() && column.isInsertable()) {
                sql.append(column.getColumnHolder("record") + ",");
            }*/
            // end
        }

        sql.append("</trim>");
        sql.append("</foreach>");
        return sql.toString();
    }

    public String insertUseGeneratedKeys(MappedStatement ms) {
        Class<?> entityClass = this.getEntityClass(ms);
        StringBuilder sql = new StringBuilder();
        sql.append(SqlHelper.insertIntoTable(entityClass, this.tableName(entityClass)));
        sql.append(SqlHelper.insertColumns(entityClass, true, false, false));
        sql.append(SqlHelper.insertValuesColumns(entityClass, true, false, false));
        return sql.toString();
    }
}

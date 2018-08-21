//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package tk.mybatis.mapper.provider.base;

import java.util.Iterator;
import java.util.Set;

import git.soulbgm.common.annotation.IdBuild;
import git.soulbgm.common.enumerate.IdType;
import org.apache.ibatis.mapping.MappedStatement;
import tk.mybatis.mapper.MapperException;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.mapperhelper.EntityHelper;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;
import tk.mybatis.mapper.mapperhelper.SelectKeyHelper;
import tk.mybatis.mapper.mapperhelper.SqlHelper;

public class BaseInsertProvider extends MapperTemplate {
    public BaseInsertProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    public String insert(MappedStatement ms) {
        Class<?> entityClass = this.getEntityClass(ms);
        StringBuilder sql = new StringBuilder();
        Set<EntityColumn> columnList = EntityHelper.getColumns(entityClass);
        this.processKey(sql, entityClass, ms, columnList);
        sql.append(SqlHelper.insertIntoTable(entityClass, this.tableName(entityClass)));
        sql.append(SqlHelper.insertColumns(entityClass, false, false, false));
        sql.append("<trim prefix=\"VALUES(\" suffix=\")\" suffixOverrides=\",\">");
        Iterator var5 = columnList.iterator();

        while(var5.hasNext()) {
            EntityColumn column = (EntityColumn)var5.next();
            if (column.isInsertable()) {
                if (column.isIdentity()) {
                    sql.append(SqlHelper.getIfCacheNotNull(column, column.getColumnHolder((String)null, "_cache", ",")));
                } else {
                    sql.append(SqlHelper.getIfNotNull(column, column.getColumnHolder((String)null, (String)null, ","), this.isNotEmpty()));
                }

                if (column.isIdentity()) {
                    sql.append(SqlHelper.getIfCacheIsNull(column, column.getColumnHolder() + ","));
                } else {
                    sql.append(SqlHelper.getIfIsNull(column, column.getColumnHolder((String)null, (String)null, ","), this.isNotEmpty()));
                }
            }
        }

        sql.append("</trim>");
        return sql.toString();
    }

    public String insertSelective(MappedStatement ms) {
        Class<?> entityClass = this.getEntityClass(ms);
        StringBuilder sql = new StringBuilder();
        Set<EntityColumn> columnList = EntityHelper.getColumns(entityClass);
        this.processKey(sql, entityClass, ms, columnList);
        sql.append(SqlHelper.insertIntoTable(entityClass, this.tableName(entityClass)));
        sql.append("<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">");
        Iterator var5 = columnList.iterator();

        EntityColumn column;
        while(var5.hasNext()) {
            column = (EntityColumn)var5.next();
            if (column.isInsertable()) {
                if (column.isIdentity()) {
                    sql.append(column.getColumn() + ",");
                } else {
                    sql.append(SqlHelper.getIfNotNull(column, column.getColumn() + ",", this.isNotEmpty()));
                }
            }
        }

        sql.append("</trim>");
        sql.append("<trim prefix=\"VALUES(\" suffix=\")\" suffixOverrides=\",\">");
        var5 = columnList.iterator();

        while(var5.hasNext()) {
            column = (EntityColumn)var5.next();
            if (column.isInsertable()) {
                if (column.isIdentity()) {
                    sql.append(SqlHelper.getIfCacheNotNull(column, column.getColumnHolder((String)null, "_cache", ",")));
                } else {
                    sql.append(SqlHelper.getIfNotNull(column, column.getColumnHolder((String)null, (String)null, ","), this.isNotEmpty()));
                }

                if (column.isIdentity()) {
                    sql.append(SqlHelper.getIfCacheIsNull(column, column.getColumnHolder() + ","));
                }
            }
        }

        sql.append("</trim>");
        return sql.toString();
    }

    private void processKey(StringBuilder sql, Class<?> entityClass, MappedStatement ms, Set<EntityColumn> columnList) {
        Boolean hasIdentityKey = false;
        Iterator var6 = columnList.iterator();

        EntityColumn column;
        label34:
        do {
            while(true) {
                while(var6.hasNext()) {
                    column = (EntityColumn)var6.next();

                    // 更改此处 start
                    IdBuild idBuild = column.getEntityField().getAnnotation(IdBuild.class);
                    if (idBuild != null && idBuild.idType() == IdType.JDBC) {
                        sql.append(SqlHelper.getBindCache(column));
                        if (hasIdentityKey) {
                            continue label34;
                        }

                        SelectKeyHelper.newSelectKeyMappedStatement(ms, column, entityClass, this.isBEFORE(), this.getIDENTITY(column));
                        hasIdentityKey = true;
                    }
                    // end

                    if (column.isIdentity()) {
                        sql.append(SqlHelper.getBindCache(column));
                        if (hasIdentityKey) {
                            continue label34;
                        }

                        SelectKeyHelper.newSelectKeyMappedStatement(ms, column, entityClass, this.isBEFORE(), this.getIDENTITY(column));
                        hasIdentityKey = true;
                    } else if (column.getGenIdClass() != null) {
                        sql.append("<bind name=\"").append(column.getColumn()).append("GenIdBind\" value=\"@tk.mybatis.mapper.genid.GenIdUtil@genId(");
                        sql.append("_parameter").append(", '").append(column.getProperty()).append("'");
                        sql.append(", @").append(column.getGenIdClass().getCanonicalName()).append("@class");
                        sql.append(", '").append(this.tableName(entityClass)).append("'");
                        sql.append(", '").append(column.getColumn()).append("')");
                        sql.append("\"/>");
                    }
                }

                return;
            }
        } while(column.getGenerator() != null && column.getGenerator().equals("JDBC"));

        throw new MapperException(ms.getId() + "对应的实体类" + entityClass.getCanonicalName() + "中包含多个MySql的自动增长列,最多只能有一个!");
    }
}

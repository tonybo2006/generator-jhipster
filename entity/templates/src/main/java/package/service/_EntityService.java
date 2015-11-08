package <%=packageName%>.service;
<%  var instanceType = (dto == 'mapstruct') ? entityClass + 'DTO' : entityClass;
    var instanceName = (dto == 'mapstruct') ? entityInstance + 'DTO' : entityInstance;
    var mapper = entityInstance  + 'Mapper';
    var dtoToEntity = mapper + '.'+ entityInstance +'DTOTo' + entityClass;
    var entityToDto = mapper + '.'+ entityInstance +'To' + entityClass + 'DTO';
    var entityToDtoReference = mapper + '::'+ entityInstance +'To' + entityClass + 'DTO'; %>
import <%=packageName%>.domain.<%= entityClass %>;<% } if (dto == 'mapstruct') { %>
import <%=packageName%>.web.rest.dto.<%= entityClass %>DTO;<% } %><% if (pagination != 'no') { %>
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;<% } %>
<% if (dto == 'mapstruct') { %>
import java.util.LinkedList;<% } %>
import java.util.List;

/**
 * Service Interface for managing <%= entityClass %>.
 */
public Interface <%= entityClass %>Service {

    /**
     * Save a <%= entityInstance %>.
     * @param input the <%= instanceType %>
     * @return the persisted entity
     */
    public <%= instanceType %> save<%= entityClass %>(<%= instanceType %> <%= instanceName %>);

    /**
     *  get all the <%= entityInstance %>s.
     *  @return the list of entities
     */
    public <% if (pagination != 'no') { %>Page<<%= entityClass %>% } else { %>List<<%= instanceType %><% } %>> findAll(<% if (pagination != 'no') { %>Pageable pageable<% } %>);
<% for (idx in relationships) { if (relationships[idx].relationshipType == 'one-to-one' && relationships[idx].ownerSide != true) { -%>
    /**
     *  get all the <%= entityInstance %>s where <%= relationships[idx].relationshipNameCapitalized %> is null.
     *  @return the list of entities
     */
    public List<<%= instanceType %>> findAllWhere<%= relationships[idx].relationshipNameCapitalized %>IsNull();
<% } } -%>

    /**
     *  get the "id" <%= entityInstance %>.
     *  @param input id
     *  @return the entity
     */
    public <%= instanceType %> findOne(<%= pkType %> id);

    /**
     *  delete the "id" <%= entityInstance %>.
     *  @param input id
     */
    public void delete(<%= pkType %> id);<% if (searchEngine == 'elasticsearch') { %>

    /**
     * search for the <%= entityInstance %> corresponding
     * to the query.
     * @param input query
     */
    public List<<%= instanceType %>> search(String query);<% } %>
}

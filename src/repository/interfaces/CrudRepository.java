package repository.interfaces;

public interface CrudRepository <ID, E> {

    boolean add(E entity);
    E remove(ID id);
    void update(E newEntity, ID id);
    E find(ID id);

}

import javax.persistence.*;

import static javax.persistence.AccessType.FIELD;
import static javax.persistence.FetchType.EAGER;

import java.io.Serializable;
import java.util.Set;

@Entity
@Access(FIELD)
public class State extends Business implements Serializable{
	
	public State() {
    }
	
    /** Primary key. */
    @Id
    @OneToOne(mappedBy="id", optional=true, cascade={CascadeType.REFRESH,CascadeType.REMOVE})
    private int id;

    /** Name attribute. */
    @Column(name="name", length=200, nullable=false)
    private String name;
    
    @Basic
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    
    @Basic
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}

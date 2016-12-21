import javax.persistence.*;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

import static javax.persistence.AccessType.FIELD;
import static javax.persistence.FetchType.EAGER;

@Entity
@Access(FIELD)
@Inheritance(strategy=InheritanceType.JOINED)
public class Business implements Serializable {
	
	public Business() {
    }
	
    /** Primary key. */
    @Id
    private int id;   

    /** Name attribute. */
    @Column(name="name", length=200, nullable=false)
    private String name;

    @ElementCollection(fetch=EAGER)    
    @CollectionTable(name="Logo", joinColumns=@JoinColumn(name="Business"))
    @Column(name="Logo", nullable=true)
    private ArrayList<File> Logo;    
    
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

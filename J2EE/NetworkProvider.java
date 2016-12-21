import javax.persistence.*;

import static javax.persistence.AccessType.FIELD;
import static javax.persistence.FetchType.EAGER;

import java.io.Serializable;
import java.util.Set;

@Entity
@Access(FIELD)
public class NetworkProvider extends Business implements Serializable{
	
	public NetworkProvider() {
    }
	
	/** Primary key. */
    @Id
    @OneToOne(mappedBy="id", optional=true, cascade={CascadeType.REFRESH,CascadeType.REMOVE})
    private int id;    
    
    @Basic
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
        
}

package adaptavant.digistore;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;

import adaptavant.digistore.PMF;
import adaptavant.digistore.Employee;

@Controller
public class DigiStoreController {
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String getWelcomePage(ModelMap model) {
 
		return "index";
 	}
	@RequestMapping(value = "/signin", method = RequestMethod.GET)
	public String getSiginPage(ModelMap model) {
 
		return "signin";
 	}
	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public ModelAndView signin(HttpServletRequest request, ModelMap model) {
		String empid = request.getParameter("empid");
		String pass = request.getParameter("password");
		List<Employee> results = null;
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		HttpSession session = request.getSession(true);
		//System.out.println(uname+pass);
		try {
			Query q = pm.newQuery(Employee.class,"empid == "+empid+" & password == '" +pass+"'");
			results = (List<Employee>) q.execute();
			System.out.println(results);	
			if (!results.isEmpty()) {
				System.out.println("Logged in");
				//results.get(0).incrementLogins();
				Employee employee = pm.getObjectById(Employee.class,results.get(0).getEmpid());
				session.setAttribute("fname", employee.getFirstName());
				session.setAttribute("lname", employee.getLastName());
				session.setAttribute("emailid", employee.getEmailId());
				session.setAttribute("empid", employee.getEmpid());
//				session.setAttribute("logins", employee.getLogins());
//				session.setAttribute("username",uname);
//				session.setAttribute("blob-key", employee.getBlobKeyString());
				//return new ModelAndView("redirect:home/"+empid);
			}
		}catch(Exception e){
			e.printStackTrace();
		}	
		finally {
			pm.close();
		}
		return new ModelAndView("redirect:/getProducts");
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String getSignUpPage(ModelMap model) {
 
		return "signup";
 	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ModelAndView signUp(HttpServletRequest request, ModelMap model) {
 
//		String username = request.getParameter("uname");
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String password= request.getParameter("password");
		String email= request.getParameter("email");
		
		Employee employee = new Employee();
		
//		e.setUsername(username);
		employee.setFirstName(fname);
		employee.setLastName(lname);
		employee.setPassword(password);
		employee.setEmailId(email);
//		e.setLoginDate(new Date());
		
		HttpSession session = request.getSession(true);
		session.setAttribute("fname", employee.getFirstName());
		session.setAttribute("lname", employee.getLastName());
		session.setAttribute("emailid", employee.getEmailId());
		session.setAttribute("empid", employee.getEmpid());
 
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(employee);
		} finally {
			pm.close();
		}
		return new ModelAndView("redirect:/getProducts");
	}

	@RequestMapping(value = "/addProduct", method = RequestMethod.GET)
	public String getAddProductPage(ModelMap model) {
 
		return "addProduct";
 	}

	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	public ModelAndView addProduct(HttpServletRequest request, ModelMap model) {
		String brand = request.getParameter("brand");
		String type = request.getParameter("type");
		String mname = request.getParameter("mname");
		String price = request.getParameter("price");
		String nua = request.getParameter("nua");
		String bks;
		
		Products product = new Products();
		product.setBrand(brand);
		product.setType(type);
		product.setName(mname);
		product.setPrice(Long.valueOf(price).longValue());
		product.setUnitsAvailable(Integer.valueOf(nua).intValue());
		
		BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
		Map<String, BlobKey> blobs = blobstoreService.getUploadedBlobs(request);
                
        if(blobs!=null)
		{
			BlobKey blobKey = blobs.get("mypic");
			bks = blobKey.getKeyString();
			product.setBlobKey(bks);
		}
        
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(product);
		} finally {
			pm.close();
		}
		return new ModelAndView("redirect:/getProducts");
 	}
	
	@RequestMapping(value = "/serve", method = RequestMethod.GET)
	public void serveBlob(HttpServletRequest req, HttpServletResponse res, ModelMap model) {
		BlobKey blobKey = new BlobKey(req.getParameter("blob-key"));
        try {
        	BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
			blobstoreService.serve(blobKey, res);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 	}
	
	@RequestMapping(value = "/updateProduct", method = RequestMethod.GET)
	public String getUpdateProductPage(HttpServletRequest req, ModelMap model) {
		List<Products> results = null;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			Query q = pm.newQuery(Products.class); //,"username == '*'"
			results = (List<Products>) q.execute(); 
		}catch(Exception e){
			e.printStackTrace(); 
		}finally {
			pm.close();
		}
		List<Long> pids = new ArrayList<Long>();
		if(results!=null){
			for(Products p : results)
			{
				pids.add(p.getProductId());
			}
		}
		req.setAttribute("productIds", pids);
		return "updateProduct";
 	}
	
		
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView UpdateUserDetails( HttpServletRequest request, ModelMap model) {
	
		HttpSession session = request.getSession(true);
		String brand = request.getParameter("brand");
		String type = request.getParameter("type");
		String mname = request.getParameter("mname");
		String price = request.getParameter("price");
		String nua = request.getParameter("nua");
		String bks;
		String productId = request.getParameter("productIds");
	    
	    BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
	    Map<String, BlobKey> blobs = blobstoreService.getUploadedBlobs(request);
        BlobKey blobKey = blobs.get("mypic");
        PersistenceManager pm = PMF.get().getPersistenceManager();
	    
	    try {
	    	Query q =pm.newQuery(Products.class,"productId == "+productId);
	    	List<Products> results = (List<Products>) q.execute();
	    	Products product = pm.getObjectById(Products.class,productId);//Products.class,results.get(0).getProductId()); 
	    	product.setBrand(brand);
			product.setType(type);
			//product.setName(mname);
			product.setPrice(Long.valueOf(price).longValue());
			product.setUnitsAvailable(Integer.valueOf(nua).intValue());
	        
			if (blobs!=null) 
	        {
	        	bks = blobKey.getKeyString();
	        	product.setBlobKey(blobKey.getKeyString());
	        }
			pm.makePersistent(product);
	    } 
	    catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
	    finally {
	        pm.close();
	    }   

	    return new ModelAndView("redirect:/getProducts");
 	}
	
	@RequestMapping(value = "/getProducts", method = RequestMethod.GET)
	public void getProducts(HttpServletRequest req, HttpServletResponse res, ModelMap model) {
		List<Products> results = null;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		try {
			Query q = pm.newQuery(Products.class); //,"username == '*'"
			results = (List<Products>) q.execute(); 
			req.setAttribute("ProductsList", results);
		}catch(Exception e){
			e.printStackTrace(); 
		}finally {
			pm.close();
		}
		
		if(req.getAttribute("ProductsList")!=null)
		{
			RequestDispatcher rd = req.getRequestDispatcher("/home");
			try {
				rd.forward(req, res);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
//		req.setAttribute("ProductsList", results);
//		return new ModelAndView("redirect:/home");
 	}
	
	
}

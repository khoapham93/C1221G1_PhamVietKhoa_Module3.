package controller;

import dto.ContractDTO;
import dto.CustomerDTO;
import dto.EmployeeDTO;
import dto.FacilityDTO;
import models.AcademicLevel;
import models.Contract;
import models.Department;
import models.Position;
import service.IContractService;
import service.ICustomerService;
import service.IEmployeeService;
import service.IFacilityService;
import service.impl.ContractServiceImpl;
import service.impl.CustomerServiceImpl;
import service.impl.EmployeeServiceImpl;
import service.impl.FacilityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@WebServlet(name = "ContractController",urlPatterns = "/contracts")
public class ContractController extends HttpServlet {
    IContractService iContractService = new ContractServiceImpl();
    IFacilityService iFacilityService = new FacilityServiceImpl();
    ICustomerService iCustomerService = new CustomerServiceImpl();
    IEmployeeService iEmployeeService = new EmployeeServiceImpl();
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createContract(request, response);
                break;
            default:
                goListContract(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                goCreateContract(request, response);
                break;
            default:
                goListContract(request, response);
        }
    }

    private void goCreateContract(HttpServletRequest request, HttpServletResponse response) {
        List<EmployeeDTO> employeeDTOList = iEmployeeService.getList();
       List<CustomerDTO> customerDTOList = iCustomerService.getList();
       List<FacilityDTO> facilityDTOList = iFacilityService.getList();
        try {
            request.setAttribute("urlPath", "contract");
            request.setAttribute("employeeList", employeeDTOList);
            request.setAttribute("customerList", customerDTOList);
            request.setAttribute("facilityList", facilityDTOList);
            request.getRequestDispatcher("/view/contract/create.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void goListContract(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<ContractDTO> contractDTOList = iContractService.getList();
            request.setAttribute("urlPath", "contract");
            request.setAttribute("contractList", contractDTOList);
            request.getRequestDispatcher("/view/contract/list.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void createContract(HttpServletRequest request, HttpServletResponse response) {
        LocalDate startDate = null;
        try {
            startDate = LocalDate.parse(request.getParameter("startDate"));
        }catch (Exception e){
            e.printStackTrace();
        }
        LocalDate endDate =null;
        try {
            endDate = LocalDate.parse(request.getParameter("endDate"));
        }catch (Exception e){
            e.printStackTrace();
        }

        Double deposit = null;
        try {
            deposit = Double.valueOf(request.getParameter("deposit"));
        }catch (Exception e){
            e.printStackTrace();
        }

        Double total = null;

        Integer employeeId =null;
        try {
            employeeId = Integer.valueOf(request.getParameter("employeeId"));
        }catch (Exception e){
            e.printStackTrace();
        }
        Integer customerId = null;
        try {
            customerId = Integer.valueOf(request.getParameter("customerId"));
        }catch (Exception e){
            e.printStackTrace();
        }
        Integer facilityId=null;
        try {
            facilityId = Integer.valueOf(request.getParameter("facilityId"));
        }catch (Exception e){
            e.printStackTrace();
        }

        Contract contract = new Contract(startDate,endDate,deposit,total,employeeId,customerId,facilityId);

        Map<String, String> map = iContractService.save(contract);

        if (map.isEmpty()) {
            try {
                request.setAttribute("message", "Contract was create successfully!");
                request.setAttribute("urlPath", "contract");
                request.getRequestDispatcher("/view/contract/create.jsp").forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        } else {
            request.setAttribute("error", map);
            request.setAttribute("contract", contract);
            goCreateContract(request, response);

        }
    }

}

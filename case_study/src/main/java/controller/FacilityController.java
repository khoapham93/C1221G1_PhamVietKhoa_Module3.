package controller;

import dto.EmployeeDTO;
import dto.FacilityDTO;
import models.Facility;
import models.FacilityType;
import models.RentType;
import service.IFacilityService;
import service.IFacilityTypeService;
import service.IRentTypeService;
import service.impl.FacilityServiceImpl;
import service.impl.FacilityTypeServiceImpl;
import service.impl.RentTypeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "FacilityController", urlPatterns = "/facility")
public class FacilityController extends HttpServlet {
    private IFacilityService iFacilityService = new FacilityServiceImpl();
    private IRentTypeService iRentTypeService = new RentTypeServiceImpl();
    private IFacilityTypeService iFacilityTypeService = new FacilityTypeServiceImpl();

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
                createFacility(request, response);
                break;
            case "edit":
//                editCustomer(request, response);
                break;
            case "delete":
//                deleteCustomer(request, response);
                break;
            default:
                goListFacility(request, response);
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
                goCreateFacility(request, response);
                break;
            case "edit":
//                goEditCustomer(request, response);
                break;
            case "search":
//                goSearchCustomer(request, response);
                break;
            case "view":
//                goDetailCustomer(request, response);
                break;
            default:
                goListFacility(request, response);
        }
    }

    private void goCreateFacility(HttpServletRequest request, HttpServletResponse response) {
        List<RentType> rentTypeList = iRentTypeService.getList();
        List<FacilityType> facilityTypeList = iFacilityTypeService.getList();

        try {
            request.setAttribute("urlPath", "service");
            request.setAttribute("rentTypeList", rentTypeList);
            request.setAttribute("facilityTypeList", facilityTypeList);
            request.getRequestDispatcher("/view/facility/create.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void goListFacility(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<FacilityDTO> facilityDTOList = iFacilityService.getList();
            request.setAttribute("urlPath", "service");
            request.setAttribute("facilityList", facilityDTOList);
            request.getRequestDispatcher("/view/facility/list.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void createFacility(HttpServletRequest request, HttpServletResponse response) {

        Integer facilityTypeId = null;
        try {
            facilityTypeId = Integer.valueOf(request.getParameter("facilityTypeId"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Integer rentTypeId = null;
        try {
            rentTypeId = Integer.valueOf(request.getParameter("rentTypeId"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        String code = request.getParameter("code");
        String name = request.getParameter("name");

        Double floorSquare = null;
        try {
            floorSquare = Double.valueOf(request.getParameter("floorSquare"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Double rentalFee = null;
        try {
            rentalFee = Double.valueOf(request.getParameter("rentalFee"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Integer maximumPeople = null;
        try {
            maximumPeople = Integer.valueOf(request.getParameter("maximumPeople"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        String roomStandard = request.getParameter("roomStandard");
        String description = request.getParameter("description");
        Double poolSquare = null;
        try {
            poolSquare = Double.valueOf(request.getParameter("poolSquare"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Integer numberFloor = null;
        try {
            numberFloor = Integer.valueOf(request.getParameter("numberFloor"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Facility facility =
                new Facility(facilityTypeId,
                        rentTypeId,
                        code,
                        name,
                        floorSquare,
                        rentalFee,
                        maximumPeople,
                        roomStandard,
                        description,
                        poolSquare,
                        numberFloor);

        Map<String, String> map = iFacilityService.save(facility);

        if (map.isEmpty()) {
            try {
                request.setAttribute("message", "Service was create successfully!");
                request.setAttribute("urlPath", "service");
                request.getRequestDispatcher("/view/facility/create.jsp").forward(request, response);
            } catch (ServletException | IOException e) {
                e.printStackTrace();
            }
        } else {
            request.setAttribute("error", map);
            request.setAttribute("facility", facility);
            goCreateFacility(request, response);
        }
    }
}

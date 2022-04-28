package controller;

import dto.MotelDTO;
import model.Motel;
import model.RentType;
import service.IMotelService;
import service.IRentTypeService;
import service.impl.MotelServiceImpl;
import service.impl.RentTypeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@WebServlet(name = "ProductController", urlPatterns = "/motels")
public class MotelController extends HttpServlet {
    private IMotelService iMotelService = new MotelServiceImpl();
    private IRentTypeService iRentTypeService = new RentTypeServiceImpl();

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
                createMotelRental(request, response);
                break;
            case "delete":
                deleteMotelRental(request, response);
                break;
            case "deleteMultiple":
                deleteMultiple(request, response);
                break;
            default:
                goListMotelRental(request, response);
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
                goCreateMotelRental(request, response);
                break;
            case "search":
                goSearchMotelRental(request, response);
            default:
                goListMotelRental(request, response);
        }
    }

    private void goSearchMotelRental(HttpServletRequest request, HttpServletResponse response) {
        try {
            String code = "";
//            String code = request.getParameter("code");
            String name = request.getParameter("name");
            String phone = request.getParameter("phone");

            List<MotelDTO> motelDTOList = iMotelService.search(code, name, phone);
            request.setAttribute("motelList", motelDTOList);
            request.getRequestDispatcher("/view/list.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void goCreateMotelRental(HttpServletRequest request, HttpServletResponse response) {
        List<RentType> rentTypeList = iRentTypeService.getList();

        try {
            request.setAttribute("urlPath", "employee");
            request.setAttribute("rentTypeList", rentTypeList);
            request.getRequestDispatcher("/view/create.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void goListMotelRental(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<MotelDTO> motelDTOList = iMotelService.getList();

            request.setAttribute("motelList", motelDTOList);
            request.getRequestDispatcher("/view/list.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteMotelRental(HttpServletRequest request, HttpServletResponse response) {
        Integer id = null;
        try {
            id = Integer.valueOf(request.getParameter("id"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (id != null) {
            boolean checkDelete = iMotelService.remove(id);
            if (!checkDelete) {
                try {
                    response.sendRedirect("/motels");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                request.setAttribute("message", "delete successfully!");
                goListMotelRental(request, response);
            }
        } else {
            try {
                response.sendRedirect("/motels");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void createMotelRental(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");

        LocalDate startDate = null;
        try {
            startDate = LocalDate.parse(request.getParameter("startDate"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Integer rentTypeId = null;
        try {
            rentTypeId = Integer.valueOf(request.getParameter("rentTypeId"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String description = request.getParameter("description");

        Motel motel = new Motel(name, phone, startDate, rentTypeId, description);
        Map<String, String> map = iMotelService.save(motel);

        if (map.isEmpty()) {

            request.setAttribute("message", "Retal information was create successfully!");
            goCreateMotelRental(request, response);

        } else {
            request.setAttribute("error", map);
            request.setAttribute("motel", motel);
            goCreateMotelRental(request, response);

        }

    }

    private void deleteMultiple(HttpServletRequest request, HttpServletResponse response) {
        String idList = request.getParameter("idDeleteMultiple");
        if (idList == null || "".equals(idList)) {
            try {
                response.sendRedirect("/motels");
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            String[] list = idList.split(",");
            int[] toBeDeleted = new int[list.length];
            for (int i = 0; i < list.length; i++) {
                toBeDeleted[i] = Integer.parseInt(list[i]);
            }
            boolean checkDelete = iMotelService.removeMultiple(toBeDeleted);
            if (!checkDelete) {
                try {
                    response.sendRedirect("/motels");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                request.setAttribute("message", "delete successfully!");
                goListMotelRental(request, response);
            }

        }

    }
}

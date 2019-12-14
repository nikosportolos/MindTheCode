using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Timesheet.Data;
using Timesheet.Models.StatisticsModel;

namespace Timesheet.Controllers
{
    public class StatisticsController : Controller
    {
        private readonly ApplicationDbContext _dbContext;

        public StatisticsController(ApplicationDbContext dbContext)
        {
            _dbContext = dbContext;
        }

        public ActionResult Index()
        {
            return View();
        }

        public IActionResult ProjectCostChart()
        {
            //Random rnd = new Random();
            //var lstModel = new List<SimpleReportViewModel>();
            //lstModel.Add(new SimpleReportViewModel
            //{
            //    Department = "Beer",
            //    TotalCost = rnd.Next(10)
            //});
            //lstModel.Add(new SimpleReportViewModel
            //{
            //    Department = "Wine",
            //    TotalCost = rnd.Next(10)
            //});
            //lstModel.Add(new SimpleReportViewModel
            //{
            //    Department = "Whisky",
            //    TotalCost = rnd.Next(10)
            //});
            //lstModel.Add(new SimpleReportViewModel
            //{
            //    Department = "Water",
            //    TotalCost = rnd.Next(10)
            //});

            //return View(lstModel);

            var department_partialCost = from user in _dbContext.Users
                                         join entry in _dbContext.TimesheetEntries on user.Id equals entry.User.Id
                                         join project in _dbContext.Projects on entry.ProjectId equals project.Id
                                         join department in _dbContext.Departments on project.DepartmentOwnerId equals department.Id
                                         select new { department.Name, Cost = user.CostPerHour * entry.HoursWorked };

            var groupCostByDepartment = from result in department_partialCost
                                        group result by result.Name into departments
                                        select new { departments.Key, TotalCost = departments.Sum(d => d.Cost) };

            List<string> departmentNames = new List<string>();
            List<double> totalCosts = new List<double>();
            List<SimpleReportViewModel> lstModel = new List<SimpleReportViewModel>();

            foreach (var element in groupCostByDepartment)
            {
                departmentNames.Add(element.Key);
                totalCosts.Add(element.TotalCost);

                lstModel.Add(new SimpleReportViewModel
                {
                    Department = element.Key,
                    TotalCost = element.TotalCost
                });
            }


            ViewBag.Labels = departmentNames;
            ViewBag.Costs = totalCosts;

            return View(lstModel);
        }

        public IActionResult ProjectTimeChart()
        {
            return View();
        }


        public IActionResult ProjectOverviewChart()
        {
            return View();
        }

    }
}
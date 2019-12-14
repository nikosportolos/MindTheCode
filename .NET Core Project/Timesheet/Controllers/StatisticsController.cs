using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Timesheet.Models.StatisticsModel;

namespace Timesheet.Controllers
{
    public class StatisticsController : Controller
    {

        public ActionResult Index()
        {
            return View();
        }

        public IActionResult ProjectCostChart()
        {

            Random rnd = new Random();
            var lstModel = new List<SimpleReportViewModel>();
            lstModel.Add(new SimpleReportViewModel
            {
                DimensionOne = "Beer",
                Quantity = rnd.Next(10)
            });
            lstModel.Add(new SimpleReportViewModel
            {
                DimensionOne = "Wine",
                Quantity = rnd.Next(10)
            });
            lstModel.Add(new SimpleReportViewModel
            {
                DimensionOne = "Whisky",
                Quantity = rnd.Next(10)
            });
            lstModel.Add(new SimpleReportViewModel
            {
                DimensionOne = "Water",
                Quantity = rnd.Next(10)
            });

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
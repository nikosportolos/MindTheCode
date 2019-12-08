using System;
using System.Collections.Generic;
using System.Diagnostics.CodeAnalysis;
using System.Linq;
using System.Threading.Tasks;
using Timesheet.Models.Entities;

namespace Timesheet.Models.ViewModels
{
    public class TimesheetEntryViewModel
    {
        public int ID { get; set; }

        public DateTime EntryDate { get; set; }

        [NotNull]
        public int UserID { get; set; }

        [NotNull]
        public int ProjectID { get; set; }

        public int HoursWorked { get; set; }

    }
}
